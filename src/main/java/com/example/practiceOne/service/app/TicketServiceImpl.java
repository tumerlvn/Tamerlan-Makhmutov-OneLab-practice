package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.booking.BookingDTO;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.ticket.Ticket;
import com.example.practiceOne.entities.ticket.TicketDTO;
import com.example.practiceOne.service.CustomerService;
import com.example.practiceOne.service.FlightService;
import com.example.practiceOne.utils.mappers.TicketMapper;
import com.example.practiceOne.repository.TicketRepository;
import com.example.practiceOne.service.TicketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerService customerService;
    private final FlightService flightService;
    private final TicketMapper ticketMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TicketDTO> getAllTicketsOfCustomer(Long customerId) {
        return ticketRepository
                .findAll()
                .stream()
                .filter(e -> e.getCustomer().getId().equals(customerId))
                .map(ticketMapper::mapToTicketDto)
                .toList();

    }


    @Override
    @Transactional
    @KafkaListener(groupId = "server.broadcast", topics = {"server.booking"}, containerFactory = "kafkaListenerContainerFactory")
    public void createTicketFromBooking(BookingDTO bookingDTO) {
        // Я к сожалению не совсем уверен где делать проверки, поэтому решил разделить на две части
        CustomerDTO customerDTO;
        FlightDTO flightDTO;
        if ((customerDTO = customerService.getCustomer(bookingDTO.getCustomerId())) == null) {
            log.error("Customer doesn't exist");
            return;
        } else if ((flightDTO = flightService.getFlight(bookingDTO.getFlightId())) == null) {
            log.error("Flight doesn't exist");
            return;
        }

        if (flightDTO.getDepartureTime().isBefore(LocalDate.now())) {
            log.error("Ticket sales are over!");
            return;
        }
        List<FlightDTO> flightsOfCustomer = flightService.getAllFlightsOfCustomer(customerDTO.getId());
        if (!flightsOfCustomer.stream().filter(e -> Objects.equals(e.getId(), flightDTO.getId())).findFirst().isEmpty()) {
            log.error("Ticket already bought");
            return;
        }

        customerService.updateBalanceOfUser(customerDTO.getId(), -bookingDTO.getCost());


        TicketDTO ticketDTO = TicketDTO
                .builder()
                .customerDto(customerDTO)
                .flightDto(flightDTO)
                .seatNumber(bookingDTO.getSeatNumber())
                .baggageAmount(bookingDTO.getBaggageAmount())
                .seatClass(bookingDTO.getSeatClass())
                .cost(bookingDTO.getCost())
                .build();
        ticketRepository.save(ticketMapper.mapToTicket(ticketDTO));
        log.info("Ticket has been created");
    }


    @Override
    @Transactional
    public void returnTicket(User user, Long ticketId) {
        CustomerDTO customerDTO = customerService.getCustomerByUsername(user.getUsername());
        Ticket ticket = ticketRepository.findAllByCustomerId(customerDTO.getId()).stream()
                .filter(e -> e.getId().equals(ticketId))
                .findFirst()
                .orElse(null);
        if (ticket == null) {
            log.error("You don't have such ticket");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (ticket.getFlight().getDepartureTime().minusDays(1).isBefore(LocalDate.now())) {
            log.error("Return of ticket is over");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        customerService.updateBalanceOfUser(customerDTO.getId(), ticket.getCost());

    }
}

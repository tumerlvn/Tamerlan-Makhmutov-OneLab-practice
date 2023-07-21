package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.ticket.TicketDTO;
import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.repository.FlightRepository;
import com.example.practiceOne.service.CustomerService;
import com.example.practiceOne.service.FlightService;
import com.example.practiceOne.utils.mappers.TicketMapper;
import com.example.practiceOne.repository.TicketRepository;
import com.example.practiceOne.service.TicketService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    FlightService flightService;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    Logger log;
    @Override
    @Transactional(readOnly = true)
    public List<TicketDTO> getAllTicketsOfCustomer(Long customerId) {
        return ticketRepository
                .findAll()
                .stream()
                .filter(e -> e.getCustomer().getId().equals(customerId))
                .map(e -> ticketMapper.mapToTicketDto(e))
                .toList();

    }

    @Override
    public void createTicket(Long customerId, Long flightId) {
        CustomerDTO customerDto = customerService.getCustomer(customerId);
        FlightDTO flightDto = flightService.getFlight(flightId);

        TicketDTO ticketDTO = TicketDTO
                .builder()
                .customerDto(customerDto)
                .flightDto(flightDto)
                .departureDateTime(flightDto.getDepartureTime())
                .build();
        ticketRepository.save(ticketMapper.mapToTicket(ticketDTO));
    }
}

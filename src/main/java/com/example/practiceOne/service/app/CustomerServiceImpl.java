package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.booking.BookingDTO;
import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.ticket.Ticket;
import com.example.practiceOne.repository.FlightRepository;
import com.example.practiceOne.utils.mappers.CustomerMapper;
import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.repository.TicketRepository;
import com.example.practiceOne.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    // Оказывается использовать field injection - нежелательно
    // Заменил на constructor injection с использованием RequiredArgsConstructor
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final CustomerMapper customerMapper;
    private final KafkaTemplate<Long, BookingDTO> kafkaTemplate;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::mapToCustomerDto)
                .toList();
    }

    @Override
    public List<CustomerDTO> getAllCustomersOnFlight(Long flightId) {
        return ticketRepository
                .findAllByFlightId(flightId)
                .stream()
                .map(Ticket::getCustomer)
                .map(customerMapper::mapToCustomerDto)
                .toList();
    }


    @Override
    public CustomerDTO getCustomer(Long customerId) {
        return customerMapper.mapToCustomerDto(customerRepository.findById(customerId));
    }

    @Override
    public CustomerDTO getCustomerByUsername(String username) {
        return customerMapper.mapToCustomerDto(customerRepository.findByUsername(username));
    }

    @Override
    public CustomerDTO updateBalanceOfUser(Long customerId, Float change) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            log.error("Customer doesn't exist");
            return null;
        }
        customer.setBalance(customer.getBalance() + change);
        customerRepository.save(customer);
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDTO> queryByNameEnd(String ending) {
        Customer customer = new Customer();
        customer.setUsername(ending);

        var matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatcher::endsWith)
                .withIgnoreNullValues();

        var example = Example.of(customer, matcher);
        return ((List<Customer>) customerRepository.findAll(example)).stream().map(customerMapper::mapToCustomerDto).toList();
    }

    @Override
    public void bookTicket(Long customerId, Long flightId, Integer baggageAmount, SeatClass seatClass) {
        Flight flight = flightRepository.findById(flightId).orElse(null);
        if (flight == null) {
            log.error("Flight doesn't exist");
            return;
        }
        if (getCustomer(customerId) == null) {
            return;
        }

        int lowBound;
        int upperBound;
        if (seatClass == SeatClass.first) {
            lowBound = 1;
            upperBound = 20;
        } else {
            lowBound = 21;
            upperBound = flight.getNumberOfSeats();
        }

        Optional<Integer> freeSeat = IntStream.rangeClosed(1, flight.getNumberOfSeats())
                .filter(
                        num -> ticketRepository.findAllByFlightId(flightId).stream()
                                .map(Ticket::getSeatNumber)
                                .noneMatch(n -> n == num)
                )
                .filter(n -> n >= lowBound && n <= upperBound)
                .boxed()
                .findAny();

        if (freeSeat.isEmpty()) {
            log.error("Cannot find free seat for you, try another seat class");
            return;
        }

        BookingDTO booking = BookingDTO.builder()
                .customerId(customerId)
                .flightId(flightId)
                .seatNumber(freeSeat.get())
                .baggageAmount(baggageAmount)
                .seatClass(seatClass)
                .build();



        booking.evaluate(flight.getDistance());
        if (getCustomer(customerId).getBalance() < booking.getCost()) {
            log.error("Insufficient funds. You need at least: " + booking.getCost());
            return;
        }

        kafkaTemplate.send("server.booking", booking);
    }
}

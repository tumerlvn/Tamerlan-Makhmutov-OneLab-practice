package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.booking.BookingDTO;
import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.ticket.Ticket;
import com.example.practiceOne.utils.mappers.CustomerMapper;
import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.repository.TicketRepository;
import com.example.practiceOne.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    // Оказывается использовать field injection - нежелательно
    // Заменил на constructor injection с использованием RequiredArgsConstructor
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;
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
    public void bookTicket(Long customerId, Long flightId) {
        BookingDTO booking = BookingDTO.builder().customerId(customerId).flightId(flightId).build();
        kafkaTemplate.send("server.booking", booking);
    }
}

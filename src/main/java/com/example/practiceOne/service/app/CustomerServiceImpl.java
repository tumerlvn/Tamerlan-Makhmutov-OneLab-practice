package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.ticket.Ticket;
import com.example.practiceOne.utils.mappers.CustomerMapper;
import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.repository.TicketRepository;
import com.example.practiceOne.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(e -> customerMapper.mapToCustomerDto(e))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomersOnFlight(Long flightId) {
        return ticketRepository
                .findAllByFlightId(flightId)
                .stream()
                .map(Ticket::getCustomer)
                .map(e -> customerMapper.mapToCustomerDto(e))
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getCustomer(Long customerId) {
        return customerMapper.mapToCustomerDto(customerRepository.findById(customerId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> queryByNameEnd(String ending) {
        Customer customer = new Customer();
        customer.setUsername(ending);

        var matcher = ExampleMatcher.matching()
                .withMatcher("username", match -> match.endsWith())
                .withIgnoreNullValues();

        var example = Example.of(customer, matcher);
        return ((List<Customer>) customerRepository.findAll(example)).stream().map(customerMapper::mapToCustomerDto).toList();
    }
}

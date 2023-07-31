package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.booking.BookingDTO;
import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.repository.FlightRepository;
import com.example.practiceOne.repository.TicketRepository;
import com.example.practiceOne.utils.mappers.CustomerMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

class CustomerServiceImplTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    TicketRepository ticketRepository;
    @Mock
    FlightRepository flightRepository;
    @Mock
    CustomerMapper customerMapper;
    @Mock
    KafkaTemplate<Long, BookingDTO> kafkaTemplate;
    @Mock
    Logger log;
    @InjectMocks
    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        List<CustomerDTO> result = customerServiceImpl.getAllCustomers();
        Assertions.assertEquals(List.of(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f))), result);
    }

    @Test
    void testGetAllCustomersOnFlight() {
        when(ticketRepository.findAllByFlightId(anyLong())).thenReturn(List.of(null));

        List<CustomerDTO> result = customerServiceImpl.getAllCustomersOnFlight(Long.valueOf(1));
        Assertions.assertEquals(List.of(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f))), result);
    }

    @Test
    void testGetCustomer() {
        when(customerMapper.mapToCustomerDto((Customer) any())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));

        CustomerDTO result = customerServiceImpl.getCustomer(Long.valueOf(1));
        Assertions.assertEquals(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)), result);
    }

    @Test
    void testGetCustomerByUsername() {
        when(customerRepository.findByUsername(anyString())).thenReturn(new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)));
        when(customerMapper.mapToCustomerDto((Customer) any())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));

        CustomerDTO result = customerServiceImpl.getCustomerByUsername("username");
        Assertions.assertEquals(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)), result);
    }

    @Test
    void testUpdateBalanceOfUser() {
        when(customerMapper.mapToCustomerDto((Customer) any())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));

        CustomerDTO result = customerServiceImpl.updateBalanceOfUser(Long.valueOf(1), Float.valueOf(1.1f));
        Assertions.assertEquals(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)), result);
    }

    @Test
    void testQueryByNameEnd() {
        List<CustomerDTO> result = customerServiceImpl.queryByNameEnd("ending");
        Assertions.assertEquals(List.of(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f))), result);
    }

    @Test
    void testBookTicket() {
        when(ticketRepository.findAllByFlightId(anyLong())).thenReturn(List.of(null));
        when(customerMapper.mapToCustomerDto((Customer) any())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));

        customerServiceImpl.bookTicket(Long.valueOf(1), Long.valueOf(1), Integer.valueOf(0), SeatClass.econom);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
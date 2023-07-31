package com.example.practiceOne.controllers;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.booking.BookingForm;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.ticket.TicketDTO;
import com.example.practiceOne.service.CustomerService;
import com.example.practiceOne.service.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CustomerControllerTest {
    @Mock
    CustomerService customerService;
    @Mock
    TicketService ticketService;
    @Mock
    Logger log;
    @Mock
    Authentication auth;
    @Mock
    SecurityContext securityContext;
    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<CustomerDTO> customers = List.of(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));
        when(customerService.getAllCustomers()).thenReturn(customers);

        ResponseEntity<List<CustomerDTO>> result = customerController.getAll();
        Assertions.assertEquals(new ResponseEntity<>(customers, HttpStatus.OK), result);
    }

    @Test
    void testBookFlight() {
        ResponseEntity<?> result = customerController.bookFlight(new BookingForm(Long.valueOf(1), Long.valueOf(1), Integer.valueOf(0), SeatClass.econom));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetTicketsOfCustomer() {
        when(ticketService.getAllTicketsOfCustomer(anyLong())).thenReturn(List.of(new TicketDTO(Long.valueOf(1), new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)), new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f))));

        ResponseEntity<List<TicketDTO>> result = customerController.getTicketsOfCustomer(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetTicketsOfCurrentUser() {
        when(customerService.getCustomerByUsername(anyString())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));

        List<TicketDTO> ticketDTOS = List.of(new TicketDTO(Long.valueOf(1), new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)), new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)));
        when(ticketService.getAllTicketsOfCustomer(anyLong())).thenReturn(ticketDTOS);
        when(securityContext.getAuthentication()).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(new User("username", "password", new ArrayList<>()));
        ResponseEntity<List<TicketDTO>> result = customerController.getTicketsOfCurrentUser();
        Assertions.assertEquals(new ResponseEntity<>(ticketDTOS, HttpStatus.OK), result);
    }

    @Test
    void testReturnTicket() {
        ResponseEntity<?> result = customerController.returnTicket(Long.valueOf(1));
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
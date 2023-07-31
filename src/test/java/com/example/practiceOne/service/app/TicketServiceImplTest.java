package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.booking.BookingDTO;
import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.ticket.Ticket;
import com.example.practiceOne.entities.ticket.TicketDTO;
import com.example.practiceOne.repository.TicketRepository;
import com.example.practiceOne.service.CustomerService;
import com.example.practiceOne.service.FlightService;
import com.example.practiceOne.utils.mappers.TicketMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

class TicketServiceImplTest {
    @Mock
    TicketRepository ticketRepository;
    @Mock
    CustomerService customerService;
    @Mock
    FlightService flightService;
    @Mock
    TicketMapper ticketMapper;
    @Mock
    Logger log;
    @InjectMocks
    TicketServiceImpl ticketServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTicketsOfCustomer() {
        List<TicketDTO> result = ticketServiceImpl.getAllTicketsOfCustomer(Long.valueOf(1));
        Assertions.assertEquals(List.of(new TicketDTO(Long.valueOf(1), new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)), new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f))), result);
    }

    @Test
    void testCreateTicketFromBooking() {
        when(customerService.getCustomer(anyLong())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));
        when(customerService.updateBalanceOfUser(anyLong(), anyFloat())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));
        when(flightService.getFlight(anyLong())).thenReturn(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)));
        when(flightService.getAllFlightsOfCustomer(anyLong())).thenReturn(List.of(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f))));
        when(ticketMapper.mapToTicket(any())).thenReturn(new Ticket(Long.valueOf(1), new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)), new Flight(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f), Set.of(null)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)));

        ticketServiceImpl.createTicketFromBooking(new BookingDTO(Long.valueOf(1), Long.valueOf(1), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)));
    }

    @Test
    void testReturnTicket() {
        when(ticketRepository.findAllByCustomerId(anyLong())).thenReturn(List.of(null));
        when(customerService.getCustomerByUsername(anyString())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));
        when(customerService.updateBalanceOfUser(anyLong(), anyFloat())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));

        ticketServiceImpl.returnTicket(null, Long.valueOf(1));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
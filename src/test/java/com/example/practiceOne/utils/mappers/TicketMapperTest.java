package com.example.practiceOne.utils.mappers;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.ticket.Ticket;
import com.example.practiceOne.entities.ticket.TicketDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import static org.mockito.Mockito.*;

class TicketMapperTest {
    @Mock
    Logger log;
    @Mock
    CustomerMapper customerMapper;
    @Mock
    FlightMapper flightMapper;
    @InjectMocks
    TicketMapper ticketMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapToTicketDto() {
        when(customerMapper.mapToCustomerDto((Customer) any())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));
        when(flightMapper.mapToFlightDto((Flight) any())).thenReturn(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)));

        TicketDTO result = ticketMapper.mapToTicketDto(new Ticket(Long.valueOf(1), new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)), new Flight(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f), Set.of(null)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)));
        Assertions.assertEquals(new TicketDTO(Long.valueOf(1), new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)), new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)), result);
    }

    @Test
    void testMapToTicketDto2() {
        when(customerMapper.mapToCustomerDto((Customer) any())).thenReturn(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));
        when(flightMapper.mapToFlightDto((Flight) any())).thenReturn(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)));

        TicketDTO result = ticketMapper.mapToTicketDto((Ticket) null);
        Assertions.assertEquals(new TicketDTO(Long.valueOf(1), new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)), new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)), result);
    }

    @Test
    void testMapToTicket() {
        when(customerMapper.mapToCustomer(any())).thenReturn(new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(new Ticket(Long.valueOf(1), null, new Flight(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f), Set.of(null)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)))));
        when(flightMapper.mapToFlight(any())).thenReturn(new Flight(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f), Set.of(new Ticket(Long.valueOf(1), new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)), null, Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)))));


        Ticket result = ticketMapper.mapToTicket(new TicketDTO(Long.valueOf(1), new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)), new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)));
        Assertions.assertEquals(new Ticket(Long.valueOf(1), new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)), new Flight(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f), Set.of(null)), Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
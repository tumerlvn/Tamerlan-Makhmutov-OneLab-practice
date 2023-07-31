package com.example.practiceOne.utils.mappers;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.ticket.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

class FlightMapperTest {
    @Mock
    Logger log;
    @InjectMocks
    FlightMapper flightMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMapToFlightDto() {
        FlightDTO result = flightMapper.mapToFlightDto(new Flight(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f), Set.of(new Ticket(Long.valueOf(1), new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)), null, Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)))));
        Assertions.assertEquals(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)), result);
    }

    @Test
    void testMapToFlightDto2() {
        FlightDTO result = flightMapper.mapToFlightDto(Optional.empty());
        Assertions.assertEquals(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)), result);
    }

    @Test
    void testMapToFlight() {
        Flight result = flightMapper.mapToFlight(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)));
        Assertions.assertEquals(new Flight(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f), Set.of(new Ticket(Long.valueOf(1), new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)), null, Integer.valueOf(0), Integer.valueOf(0), SeatClass.econom, Float.valueOf(1.1f)))), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
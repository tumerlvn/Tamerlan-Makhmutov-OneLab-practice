package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.repository.FlightRepository;
import com.example.practiceOne.repository.TicketRepository;
import com.example.practiceOne.utils.mappers.FlightMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class FlightServiceImplTest {
    @Mock
    FlightRepository flightRepository;
    @Mock
    TicketRepository ticketRepository;
    @Mock
    FlightMapper flightMapper;
    @InjectMocks
    FlightServiceImpl flightServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFlights() {
        List<FlightDTO> result = flightServiceImpl.getAllFlights();
        Assertions.assertEquals(List.of(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f))), result);
    }

    @Test
    void testGetFlight() {
        when(flightMapper.mapToFlightDto((Flight) any())).thenReturn(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)));

        FlightDTO result = flightServiceImpl.getFlight(Long.valueOf(1));
        Assertions.assertEquals(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f)), result);
    }

    @Test
    void testGetAllFlightsOfCustomer() {
        when(ticketRepository.findAllByCustomerId(anyLong())).thenReturn(List.of(null));

        List<FlightDTO> result = flightServiceImpl.getAllFlightsOfCustomer(Long.valueOf(1));
        Assertions.assertEquals(List.of(new FlightDTO(Long.valueOf(1), "aircraftCode", "departureCity", "arrivalCity", LocalDate.of(2023, Month.JULY, 31), LocalDate.of(2023, Month.JULY, 31), Integer.valueOf(0), Float.valueOf(1.1f))), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
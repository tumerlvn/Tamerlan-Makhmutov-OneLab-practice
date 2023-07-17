package com.example.practiceOne.utils;

import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.flight.FlightDTO;
import org.springframework.stereotype.Service;

@Service
public class FlightMappingUtils {
    public FlightDTO mapToFlightDto(Flight flight) {
        return FlightDTO
                .builder()
                .id(flight.getId())
                .aircraftCode(flight.getAircraftCode())
                .arrivalCity(flight.getArrivalCity())
                .departureCity(flight.getDepartureCity())
                .departureTime(flight.getDepartureTime())
                .build();
    }

    public Flight mapToFlight(FlightDTO dto) {
        return Flight
                .builder()
                .id(dto.getId())
                .aircraftCode(dto.getAircraftCode())
                .arrivalCity(dto.getArrivalCity())
                .departureCity(dto.getDepartureCity())
                .departureTime(dto.getDepartureTime())
                .build();
    }
}

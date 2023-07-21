package com.example.practiceOne.utils.mappers;

import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.flight.FlightDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightMapper {

    @Autowired
    Logger log;

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

    public FlightDTO mapToFlightDto(Optional<Flight> optFlight) {

        if (optFlight.isEmpty()) {
            log.error("Flight not found");
            return null;
        }
        Flight flight = optFlight.get();
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
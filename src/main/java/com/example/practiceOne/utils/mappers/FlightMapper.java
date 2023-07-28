package com.example.practiceOne.utils.mappers;

import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.flight.FlightDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightMapper {


    private final Logger log;

    public FlightDTO mapToFlightDto(Flight flight) {
        return FlightDTO
                .builder()
                .id(flight.getId())
                .aircraftCode(flight.getAircraftCode())
                .arrivalCity(flight.getArrivalCity())
                .departureCity(flight.getDepartureCity())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .numberOfSeats(flight.getNumberOfSeats())
                .distance(flight.getDistance())
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
                .arrivalTime(flight.getArrivalTime())
                .numberOfSeats(flight.getNumberOfSeats())
                .distance(flight.getDistance())
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
                .arrivalTime(dto.getArrivalTime())
                .numberOfSeats(dto.getNumberOfSeats())
                .distance(dto.getDistance())
                .build();
    }

}

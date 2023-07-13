package com.example.practiceOne.entities.flight;


import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FlightRepository {

    private List<FlightDTO> flights;

    public FlightRepository() {
        flights = new ArrayList<>();
        flights.add(FlightDTO.builder().id(1L)
                .aircraftCode("A338")
                .departureCity("New York")
                .arrivalCity("Tokyo").build());
        flights.add(FlightDTO.builder().id(2L)
                .aircraftCode("B37M")
                .departureCity("Tokyo")
                .arrivalCity("Almaty").build());
    }

    public FlightDTO getFlightById(Long id) {
        for (FlightDTO flight : flights) {
            if (Objects.equals(flight.getId(), id)) {
                return flight;
            }
        }
        return null;
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

}

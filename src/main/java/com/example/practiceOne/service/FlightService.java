package com.example.practiceOne.service;

import com.example.practiceOne.entities.flight.FlightDTO;

import java.util.List;

public interface FlightService {
    public List<FlightDTO> getAllFlights();

    public FlightDTO getFlight(Long flightId);
}

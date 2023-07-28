package com.example.practiceOne.controllers;

import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public List<FlightDTO> getAll() {
        return flightService.getAllFlights();
    }
}

package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.repository.FlightRepository;
import com.example.practiceOne.service.FlightService;
import com.example.practiceOne.utils.mappers.FlightMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository
                .findAll()
                .stream()
                .map(e -> flightMapper.mapToFlightDto(e))
                .toList();
    }

    @Override
    public FlightDTO getFlight(Long flightId) {
        return flightMapper.mapToFlightDto(flightRepository.findById(flightId));
    }
}

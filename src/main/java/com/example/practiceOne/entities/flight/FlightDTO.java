package com.example.practiceOne.entities.flight;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    private Long id;
    private String aircraftCode;
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
}

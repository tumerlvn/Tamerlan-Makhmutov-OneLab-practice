package com.example.practiceOne.entities.flight;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="flights")
@Data
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String aircraftCode;
    @Column
    private String departureCity;
    @Column
    private String arrivalCity;
    @Column
    private String departureTime;
}

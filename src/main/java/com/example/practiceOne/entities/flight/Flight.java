package com.example.practiceOne.entities.flight;

import com.example.practiceOne.entities.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="flights")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDate departureTime;
    @Column
    private LocalDate arrivalTime;

    @OneToMany(mappedBy = "flight",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<Ticket> tickets = new HashSet<>();
}

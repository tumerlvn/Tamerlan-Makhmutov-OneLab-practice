package com.example.practiceOne.entities.ticket;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.flight.Flight;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="tickets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column
    private Integer seatNumber;
    @Column
    private Integer baggageAmount;
    @Enumerated(EnumType.STRING)
    @Column
    private SeatClass seatClass;
}

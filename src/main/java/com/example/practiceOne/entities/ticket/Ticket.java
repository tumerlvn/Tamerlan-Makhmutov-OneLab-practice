package com.example.practiceOne.entities.ticket;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name="tickets")
@Data
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // я пока не делал ссылку на другие таблицы через JoinColumn
    // возможно пока рано
    @Column
    private Long customerId;
    @Column
    private Long flightId;
    @Column
    private String departureDateTime;
}

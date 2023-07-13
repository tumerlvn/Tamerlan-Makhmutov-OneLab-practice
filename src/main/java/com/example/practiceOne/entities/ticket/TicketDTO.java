package com.example.practiceOne.entities.ticket;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TicketDTO {
    private Long id;
    private Long customerId;
    private Long flightId;
    private String departureDateTime;
}

package com.example.practiceOne.entities.ticket;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.FlightDTO;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long id;
    private CustomerDTO customerDto;
    private FlightDTO flightDto;
    private Integer seatNumber;
    private Integer baggageAmount;
    private SeatClass seatClass;
    private Float cost;
}

package com.example.practiceOne.entities.ticket;

import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.FlightDTO;
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
}

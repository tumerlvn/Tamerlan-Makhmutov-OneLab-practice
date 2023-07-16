package com.example.practiceOne.entities.ticket;

import org.springframework.stereotype.Service;

@Service
public class TicketMappingUtils {
    public TicketDTO mapToTicketDto(Ticket ticket) {
        return TicketDTO
                .builder()
                .id(ticket.getId())
                .customerId(ticket.getCustomerId())
                .flightId(ticket.getFlightId())
                .departureDateTime(ticket.getDepartureDateTime())
                .build();
    }
    public Ticket mapToTicket(TicketDTO dto) {
        return Ticket
                .builder()
                .id(dto.getId())
                .customerId(dto.getCustomerId())
                .flightId(dto.getFlightId())
                .departureDateTime(dto.getDepartureDateTime())
                .build();
    }
}

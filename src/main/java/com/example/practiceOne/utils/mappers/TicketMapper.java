package com.example.practiceOne.utils.mappers;

import com.example.practiceOne.entities.ticket.Ticket;
import com.example.practiceOne.entities.ticket.TicketDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketMapper {


    private final Logger log;
    private final CustomerMapper customerMapper;
    private final FlightMapper flightMapper;

    public TicketDTO mapToTicketDto(Ticket ticket) {
        return TicketDTO
                .builder()
                .id(ticket.getId())
                .customerDto(customerMapper.mapToCustomerDto(ticket.getCustomer()))
                .flightDto(flightMapper.mapToFlightDto(ticket.getFlight()))
                .departureDateTime(ticket.getDepartureDateTime())
                .build();
    }

    public TicketDTO mapToTicketDto(Optional<Ticket> optTicket) {
        if (optTicket.isEmpty()) {
            log.error("Ticket not found");
            return null;
        }
        Ticket ticket = optTicket.get();
        return TicketDTO
                .builder()
                .id(ticket.getId())
                .customerDto(customerMapper.mapToCustomerDto(ticket.getCustomer()))
                .flightDto(flightMapper.mapToFlightDto(ticket.getFlight()))
                .departureDateTime(ticket.getDepartureDateTime())
                .build();
    }

    public Ticket mapToTicket(TicketDTO dto) {
        return Ticket
                .builder()
                .id(dto.getId())
                .customer(customerMapper.mapToCustomer(dto.getCustomerDto()))
                .flight(flightMapper.mapToFlight(dto.getFlightDto()))
                .departureDateTime(dto.getDepartureDateTime())
                .build();
    }
}

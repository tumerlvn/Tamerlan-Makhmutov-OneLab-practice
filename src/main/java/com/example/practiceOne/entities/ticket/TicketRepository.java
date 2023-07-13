package com.example.practiceOne.entities.ticket;

import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Stream;

@Repository
public class TicketRepository {

    private List<TicketDTO> tickets;

    @Autowired
    private FlightRepository flightRepository;

    public TicketRepository() {
        tickets = new ArrayList<>();

        tickets.add(TicketDTO.builder().id(1L)
                .customerId(1L)
                .flightId(1L)
                .departureDateTime("26-09-2020")
                .build());
        tickets.add(TicketDTO.builder().id(2L)
                .customerId(2L)
                .flightId(1L)
                .departureDateTime("26-09-2020")
                .build());
        tickets.add(TicketDTO.builder().id(3L)
                .customerId(3L)
                .flightId(2L)
                .departureDateTime("27-09-2020")
                .build());
        tickets.add(TicketDTO.builder().id(4L)
                .customerId(1L)
                .flightId(2L)
                .departureDateTime("27-09-2020")
                .build());
    }

    public List<FlightDTO> getAllFlightsOfCustomer(Long customerId) {
        List<FlightDTO> result = new ArrayList<>();
        for (TicketDTO ticket : tickets) {
            if (ticket.getCustomerId().equals(customerId)) {
                result.add(flightRepository.getFlightById(ticket.getFlightId()));
            }
        }
        return result;
    }

    public List<TicketDTO> getAllTickets() {
        return tickets;
    }
}

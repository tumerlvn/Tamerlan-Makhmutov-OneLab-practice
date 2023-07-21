package com.example.practiceOne.service;

import com.example.practiceOne.entities.ticket.TicketDTO;

import java.util.List;

public interface TicketService {
    public List<TicketDTO> getAllTicketsOfCustomer(Long customerId);
    public void createTicket(Long customerId, Long flightId);
}

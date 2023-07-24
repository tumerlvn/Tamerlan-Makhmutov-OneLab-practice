package com.example.practiceOne.service;

import com.example.practiceOne.entities.booking.BookingDTO;
import com.example.practiceOne.entities.ticket.TicketDTO;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

public interface TicketService {
    public List<TicketDTO> getAllTicketsOfCustomer(Long customerId);
    public void createTicket(Long customerId, Long flightId);

    @KafkaListener(topics = {"server.booking"})
    void createTicketFromBooking(BookingDTO bookingDTO);
}

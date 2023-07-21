package com.example.practiceOne.repository;

import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.ticket.Ticket;
import com.example.practiceOne.entities.ticket.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByFlightId(Long flightId);
}

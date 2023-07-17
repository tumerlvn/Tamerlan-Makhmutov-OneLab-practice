package com.example.practiceOne.repository;

import com.example.practiceOne.entities.flight.Flight;
import com.example.practiceOne.entities.ticket.Ticket;
import com.example.practiceOne.entities.ticket.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TicketRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlightsOfCustomer(Long customerId) {
        // не очень оптимальный запрос, но обещаю что исправлю в будущем
        return getAllTickets()
                .stream()
                .filter(e -> e.getId().equals(customerId))
                .map(e -> flightRepository.getFlightById(e.getFlightId()))
                .toList();
    }

    public List<Ticket> getAllTickets() {
        return jdbcTemplate.query(
                "SELECT * FROM tickets",
                (rs, rowNum) -> new Ticket(
                        rs.getLong("id"),
                        rs.getLong("customerId"),
                        rs.getLong("flightId"),
                        rs.getString("departureDateTime")
                )
        );
    }

    public void createTicket(TicketDTO dto) {
        String sql = "INSERT INTO tickets (customerId, flightId, departureDateTime) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, dto.getCustomerId(), dto.getFlightId(), dto.getDepartureDateTime());
    }
}

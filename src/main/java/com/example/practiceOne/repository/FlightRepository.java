package com.example.practiceOne.repository;


import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.flight.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FlightRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Flight getFlightById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM flights WHERE id = ?",
                new Object[]{id},
                (rs, rowNum) -> new Flight(
                        rs.getLong("id"),
                        rs.getString("aircraftCode"),
                        rs.getString("departureCity"),
                        rs.getString("arrivalCity"),
                        rs.getString("departureTime")
                ));
    }

    public List<Flight> getFlights() {
        return jdbcTemplate.query(
                "SELECT * FROM flights",
                (rs, rowNum) -> new Flight(
                        rs.getLong("id"),
                        rs.getString("aircraftCode"),
                        rs.getString("departureCity"),
                        rs.getString("arrivalCity"),
                        rs.getString("departureTime")
                )
        );
    }

}

package com.example.practiceOne.repository;


import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.flight.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


}

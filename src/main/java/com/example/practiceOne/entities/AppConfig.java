package com.example.practiceOne.entities;

import com.example.practiceOne.AppService;
import com.example.practiceOne.entities.customer.CustomerRepository;
import com.example.practiceOne.entities.flight.FlightRepository;
import com.example.practiceOne.entities.ticket.TicketRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {

    @Bean
    CustomerRepository customerRepository() {
        return new CustomerRepository();
    }

    @Bean
    FlightRepository flightRepository() {
        return new FlightRepository();
    }

    @Bean
    TicketRepository ticketRepository() {
        return new TicketRepository();
    }

    @Bean
    AppService appService() {
        return new AppService(customerRepository(), flightRepository(), ticketRepository());
    }
}

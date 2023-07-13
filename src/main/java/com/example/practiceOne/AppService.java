package com.example.practiceOne;

import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.customer.CustomerRepository;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.flight.FlightRepository;
import com.example.practiceOne.entities.ticket.TicketDTO;
import com.example.practiceOne.entities.ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppService {

    private TicketRepository ticketRepository;
    private FlightRepository flightRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public AppService(CustomerRepository customerRepository, FlightRepository flightRepository, TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.flightRepository = flightRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.getCustomers();
    }

    public List<TicketDTO> getAllTicketsOfCustomer(Long customerId) {
        return ticketRepository.getAllTickets().stream().filter(e -> e.getCustomerId().equals(customerId)).
        collect(Collectors.toList());
    }

    public List<CustomerDTO> getAllCustomersOnFlight(Long flightId) {
        return ticketRepository.getAllTickets().stream().filter(e -> e.getFlightId().equals(flightId))
                .map(TicketDTO::getCustomerId).map(e -> customerRepository.getCustomerById(e)).toList();
    }

    public List<FlightDTO> getAllFlights() {
        return flightRepository.getFlights();
    }
}

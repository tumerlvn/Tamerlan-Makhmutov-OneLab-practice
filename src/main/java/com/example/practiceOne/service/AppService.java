package com.example.practiceOne.service;

import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.utils.CustomerMappingUtils;
import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.utils.FlightMappingUtils;
import com.example.practiceOne.repository.FlightRepository;
import com.example.practiceOne.entities.ticket.TicketDTO;
import com.example.practiceOne.utils.TicketMappingUtils;
import com.example.practiceOne.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMappingUtils customerMappingUtils;
    @Autowired
    private FlightMappingUtils flightMappingUtils;
    @Autowired
    private TicketMappingUtils ticketMappingUtils;

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .getCustomers()
                .stream()
                .map(e -> customerMappingUtils.mapToCustomerDto(e))
                .toList();
    }

    public List<TicketDTO> getAllTicketsOfCustomer(Long customerId) {
        return ticketRepository
                .getAllTickets()
                .stream()
                .filter(e -> e.getCustomerId().equals(customerId))
                .map(e -> ticketMappingUtils.mapToTicketDto(e))
                .toList();
    }

    public List<CustomerDTO> getAllCustomersOnFlight(Long flightId) {
        return ticketRepository
                .getAllTickets()
                .stream()
                .filter(e -> e.getFlightId().equals(flightId))
                .map(e -> customerRepository.getCustomerById(e.getCustomerId()))
                .map(e -> customerMappingUtils.mapToCustomerDto(e))
                .toList();
    }

    public List<FlightDTO> getAllFlights() {
        return flightRepository
                .getFlights()
                .stream()
                .map(e -> flightMappingUtils.mapToFlightDto(e))
                .toList();
    }

    public CustomerDTO getCustomerById(Long id) {
        return customerMappingUtils.mapToCustomerDto(customerRepository.getCustomerById(id));
    }

    public void createTicket(Long customerId, Long flightId) {
        TicketDTO ticketDTO = TicketDTO
                .builder()
                .customerId(customerId)
                .flightId(flightId)
                .departureDateTime(flightRepository.getFlightById(flightId).getDepartureTime())
                .build();
        ticketRepository.createTicket(ticketDTO);
    }
}

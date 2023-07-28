package com.example.practiceOne.service;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.ticket.TicketDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    List<CustomerDTO> getAllCustomersOnFlight(Long flightId);
    CustomerDTO getCustomer(Long customerId);

    CustomerDTO getCustomerByUsername(String username);

    List<CustomerDTO> queryByNameEnd(String ending);

    void bookTicket(Long customerId, Long flightId, Integer baggageAmount, SeatClass seatClass);

}

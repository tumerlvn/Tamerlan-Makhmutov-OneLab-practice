package com.example.practiceOne.controllers;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.booking.BookingForm;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.ticket.TicketDTO;
import com.example.practiceOne.service.CustomerService;
import com.example.practiceOne.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final TicketService ticketService;

    @GetMapping("/customer/all")
    public ResponseEntity<List<CustomerDTO>> getAll() {
        try {
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customer/bookFlight")
    public ResponseEntity<?> bookFlight(@RequestBody BookingForm bookingForm) {
        customerService.bookTicket(
                bookingForm.getCustomerId(),
                bookingForm.getFlightId(),
                bookingForm.getBaggageAmount(),
                bookingForm.getSeatClass()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}/tickets")
    public ResponseEntity<List<TicketDTO>> getTicketsOfCustomer(@PathVariable Long customerId) {
        try {
            return new ResponseEntity<>(ticketService.getAllTicketsOfCustomer(customerId), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

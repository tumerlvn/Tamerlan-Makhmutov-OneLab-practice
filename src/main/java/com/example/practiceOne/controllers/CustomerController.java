package com.example.practiceOne.controllers;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.booking.BookingForm;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.ticket.TicketDTO;
import com.example.practiceOne.service.CustomerService;
import com.example.practiceOne.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/customer/my-tickets")
    public ResponseEntity<List<TicketDTO>> getTicketsOfCurrentUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            return new ResponseEntity<>(
                    ticketService.getAllTicketsOfCustomer(
                            customerService.getCustomerByUsername(user.getUsername()).getId()
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e){
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customer/return-ticket/{ticketId}")
    public ResponseEntity<?> returnTicket(@PathVariable Long ticketId) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            ticketService.returnTicket((User) auth.getPrincipal(), ticketId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResponseStatusException responseStatusException) {
            log.error(responseStatusException.toString());
            throw responseStatusException;
        } catch (Exception e) {
            log.error(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

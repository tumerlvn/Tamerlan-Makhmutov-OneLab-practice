package com.example.practiceOne.controllers;

import com.example.practiceOne.entities.additions.SeatClass;
import com.example.practiceOne.entities.booking.BookingForm;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.ticket.TicketDTO;
import com.example.practiceOne.service.CustomerService;
import com.example.practiceOne.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final TicketService ticketService;

    @GetMapping("/all")
    @ResponseBody
    public List<CustomerDTO> getAll() {
        return customerService.getAllCustomers();
    }

    @PostMapping("/bookFlight")
    public void bookFlight(@RequestBody BookingForm bookingForm) {
        customerService.bookTicket(
                bookingForm.getCustomerId(),
                bookingForm.getFlightId(),
                bookingForm.getBaggageAmount(),
                bookingForm.getSeatClass()
        );
    }

    @GetMapping("/{id}/tickets")
    public List<TicketDTO> getTicketsOfCustomer(@PathVariable Long customerId) {
        return ticketService.getAllTicketsOfCustomer(customerId);
    }
}

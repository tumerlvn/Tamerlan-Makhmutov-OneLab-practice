package com.example.practiceOne;

import com.example.practiceOne.entities.booking.BookingDTO;
import com.example.practiceOne.service.CustomerService;
import com.example.practiceOne.service.FlightService;
import com.example.practiceOne.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class PracticeOneApplication {

	private static Logger logger = LoggerFactory.getLogger(PracticeOneApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(PracticeOneApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(
			CustomerService customerService,
			FlightService flightService,
			TicketService ticketService
	) {
		return (args) -> {
			customerService.getAllCustomers().forEach(s -> logger.info(s.toString()));
			flightService.getAllFlights().forEach(s -> logger.info(s.toString()));
			ticketService.getAllTicketsOfCustomer(1L).forEach(s -> logger.info(s.toString()));
			customerService.getAllCustomersOnFlight(1L).forEach(s -> logger.info(s.toString()));
			logger.info(customerService.getCustomer(1L).toString());
			ticketService.createTicket(1L, 2L);
			customerService.bookTicket(1L, 3L);
			// Query by example
			customerService.queryByNameEnd("er").forEach(s -> logger.info(s.toString()));
//			 Non-existing ids
			ticketService.createTicket(4L,5L);
			ticketService.getAllTicketsOfCustomer(4L).forEach(s -> logger.info(s.toString()));
		};
	}




}

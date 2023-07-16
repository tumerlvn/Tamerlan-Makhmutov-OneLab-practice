package com.example.practiceOne;

import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.ticket.TicketDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class PracticeOneApplication {

	private static Logger logger = LoggerFactory.getLogger(PracticeOneApplication.class);

	public static void main(String[] args) {

//		SpringApplication.run(PracticeOneApplication.class, args);
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		AppService appService = (AppService) ctx.getBean("appService");

		appService.getAllCustomers().forEach(s -> logger.info(s.toString()));
		appService.getAllFlights().forEach(s -> logger.info(s.toString()));
		appService.getAllTicketsOfCustomer(1L).forEach(s -> logger.info(s.toString()));
		appService.getAllCustomersOnFlight(1L).forEach(s -> logger.info(s.toString()));
		logger.info(appService.getCustomerById(1L).toString());
		appService.createTicket(1L, 2L);
		// Non-existing ids
		appService.createTicket(4L,5L);
	}



}

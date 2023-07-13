package com.example.practiceOne;

import com.example.practiceOne.entities.AppConfig;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.entities.flight.FlightDTO;
import com.example.practiceOne.entities.ticket.TicketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class PracticeOneApplication {

	public static void main(String[] args) {

//		SpringApplication.run(PracticeOneApplication.class, args);
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		AppService appService = (AppService) ctx.getBean("appService");

		for (CustomerDTO customer : appService.getAllCustomers()) {
			System.out.println(customer);
		}

		System.out.println();

		for (TicketDTO ticket : appService.getAllTicketsOfCustomer(1L)) {
			System.out.println(ticket);
		}

		System.out.println();

		for (CustomerDTO customer : appService.getAllCustomersOnFlight(1L)) {
			System.out.println(customer);
		}

		System.out.println();

		for (FlightDTO flight : appService.getAllFlights()) {
			System.out.println(flight);
		}

		ctx.close();
	}

}

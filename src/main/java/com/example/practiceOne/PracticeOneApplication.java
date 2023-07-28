package com.example.practiceOne;

import com.example.practiceOne.entities.additions.SeatClass;
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
	public static void main(String[] args) {

		SpringApplication.run(PracticeOneApplication.class, args);

	}
}

package com.example.practiceOne.aop;

import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.repository.FlightRepository;
import com.example.practiceOne.service.CustomerService;
import com.example.practiceOne.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class DatabaseIntegrityAspect {

    private final Logger logger = LoggerFactory.getLogger(DatabaseIntegrityAspect.class);


    private final CustomerService customerService;
    private final FlightService flightService;

    @Pointcut("execution(* com.example.practiceOne.service.TicketService.createTicket(Long,Long)) && args(customerId, flightId)")
    public void createTicketExecution(Long customerId, Long flightId) {}

    @Around("createTicketExecution(customerId, flightId)")
    public Object checkCreationOfTicket(ProceedingJoinPoint pjp, Long customerId, Long flightId) throws Throwable {
        if (customerService.getCustomer(customerId) == null) {
            logger.error("Customer doesn't exist");
            return null;
        } else if (flightService.getFlight(flightId) == null) {
            logger.error("Flight doesn't exist");
            return null;
        } else {
            return pjp.proceed();
        }
    }
}

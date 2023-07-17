package com.example.practiceOne.aop;

import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.repository.FlightRepository;
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
public class DatabaseIntegrityAspect {

    private final Logger logger = LoggerFactory.getLogger(DatabaseIntegrityAspect.class);

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FlightRepository flightRepository;

    @Pointcut("execution(* com.example.practiceOne.service.AppService.createTicket(Long,Long)) && args(customerId, flightId)")
    public void createTicketExecution(Long customerId, Long flightId) {}

    @Around("createTicketExecution(customerId, flightId)")
    public Object checkCreationOfTicket(ProceedingJoinPoint pjp, Long customerId, Long flightId) throws Throwable {
        if (customerRepository.getCustomerById(customerId) == null) {
            logger.error("Customer doesn't exist");
            return null;
        } else if (flightRepository.getFlightById(flightId) == null) {
            logger.error("Flight doesn't exist");
            return null;
        } else {
            return pjp.proceed();
        }
    }
}

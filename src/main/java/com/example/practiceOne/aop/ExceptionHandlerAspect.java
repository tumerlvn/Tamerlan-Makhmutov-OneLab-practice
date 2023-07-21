package com.example.practiceOne.aop;

import com.example.practiceOne.PracticeOneApplication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlerAspect {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAspect.class);

    @Pointcut("execution(* com.example.practiceOne.repository.CustomerRepository.getCustomerById(..))")
    public void getCustomerByIdExecution() {}

    @Pointcut("execution(* com.example.practiceOne.repository.FlightRepository.getFlightById(..))")
    public void getFlightByIdExecution() {}

    @Around("getCustomerByIdExecution() || getFlightByIdExecution()")
    public Object afterThrowingRepository(ProceedingJoinPoint pjp) {
        Object retVal;
        try {
            retVal = pjp.proceed();
        } catch (EmptyResultDataAccessException ex) {
            // Если из базы данных ничего не приходит возвращаем null вместо ошибки
            return null;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return retVal;
    }

    @AfterThrowing(value="execution(* com.example.practiceOne.AppService.*(..))",throwing="ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex)
    {
        logger.error("After Throwing exception in method: "+joinPoint.getSignature());
        logger.error("Exception is: "+ex.getMessage());
    }
}

package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.additions.EmptyResponse;
import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.mockito.Mockito.*;

class AuthServiceImplTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    AuthServiceImpl authServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUpCustomer() {
        when(customerRepository.findByUsername(anyString())).thenReturn(new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)));
        when(customerRepository.findByEmail(anyString())).thenReturn(new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)));
        when(customerRepository.findByPassportNumber(anyString())).thenReturn(new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)));

        EmptyResponse result = authServiceImpl.signUpCustomer(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
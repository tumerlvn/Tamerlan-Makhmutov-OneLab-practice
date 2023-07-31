package com.example.practiceOne.utils.auth;

import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static org.mockito.Mockito.*;

class CustomerDetailsServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @InjectMocks
    CustomerDetailsService customerDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        when(customerRepository.findByUsername(anyString())).thenReturn(new Customer(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f), Set.of(null)));

        UserDetails result = customerDetailsService.loadUserByUsername("username");
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
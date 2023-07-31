package com.example.practiceOne.controllers;

import com.example.practiceOne.entities.additions.EmptyResponse;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class SignUpControllerTest {
    @Mock
    AuthService authService;
    @Mock
    Logger log;
    @InjectMocks
    SignUpController signUpController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignUp() {
        when(authService.signUpCustomer(any())).thenReturn(null);

        ResponseEntity<EmptyResponse> result = signUpController.signUp(new CustomerDTO(Long.valueOf(1), "username", "email", "password", "passportNumber", Float.valueOf(1.1f)));
        Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
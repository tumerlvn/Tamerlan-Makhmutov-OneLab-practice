package com.example.practiceOne.service;

import com.example.practiceOne.entities.additions.EmptyResponse;
import com.example.practiceOne.entities.customer.CustomerDTO;

public interface AuthService {
    EmptyResponse signUpCustomer(CustomerDTO customerDTO);
}

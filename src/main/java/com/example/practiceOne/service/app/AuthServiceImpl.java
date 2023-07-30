package com.example.practiceOne.service.app;

import com.example.practiceOne.entities.additions.EmptyResponse;
import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public EmptyResponse signUpCustomer(CustomerDTO customerDTO) {
        if (customerRepository.findByUsername(customerDTO.getUsername()) != null
        || customerRepository.findByEmail(customerDTO.getEmail()) != null
        || customerRepository.findByPassportNumber(customerDTO.getPassportNumber()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already exists");
        }
        Customer customer = Customer.builder()
                .username(customerDTO.getUsername())
                .email(customerDTO.getEmail())
                .password(passwordEncoder.encode(customerDTO.getPassword()))
                .passportNumber(customerDTO.getPassportNumber())
                .balance(customerDTO.getBalance())
                .build();

        customerRepository.save(customer);
        return EmptyResponse.builder()
                .status(HttpStatus.CREATED)
                .message("You are successfully registered!")
                .timeStamp(LocalDate.now())
                .build();
    }
}

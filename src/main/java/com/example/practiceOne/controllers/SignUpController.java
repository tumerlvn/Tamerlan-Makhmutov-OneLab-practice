package com.example.practiceOne.controllers;

import com.example.practiceOne.entities.additions.EmptyResponse;
import com.example.practiceOne.entities.customer.CustomerDTO;
import com.example.practiceOne.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/sign-up")
@RequiredArgsConstructor
@Slf4j
public class SignUpController {
    // i should probably make another model for output of customer
    private final AuthService authService;
    @PostMapping
    public ResponseEntity<EmptyResponse>  signUp(@RequestBody CustomerDTO customerDTO) {
        try {
            EmptyResponse response = authService.signUpCustomer(customerDTO);
            return new ResponseEntity<>(response, response.getStatus());
        } catch (ResponseStatusException responseStatusException) {
            throw responseStatusException;
        } catch (Exception e) {
            log.error(e.toString());
            EmptyResponse response = EmptyResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Something went wrong")
                    .timeStamp(LocalDate.now())
                    .build();
            return new ResponseEntity<>(response, response.getStatus());
        }

    }
}

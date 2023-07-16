package com.example.practiceOne.entities.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private Long id;
    private String username;
    private String email;
    private String hashedPassword;
    private String passportNumber;
}

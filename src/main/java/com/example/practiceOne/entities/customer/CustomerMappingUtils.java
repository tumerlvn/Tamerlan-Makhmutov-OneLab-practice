package com.example.practiceOne.entities.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMappingUtils {
    // It can be done with model mapper, but for now I will not use it, maybe later
    public CustomerDTO mapToCustomerDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .username(customer.getUsername())
                .hashedPassword(customer.getHashedPassword())
                .passportNumber(customer.getPassportNumber())
                .build();
    }

    public Customer mapToCustomer(CustomerDTO dto) {
        return Customer.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .hashedPassword(dto.getHashedPassword())
                .passportNumber(dto.getPassportNumber())
                .build();
    }
}

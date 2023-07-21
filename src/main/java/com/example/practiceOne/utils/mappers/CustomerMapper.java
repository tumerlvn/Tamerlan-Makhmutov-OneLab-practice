package com.example.practiceOne.utils.mappers;

import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.Optional;

@Service
public class CustomerMapper {
    @Autowired
    Logger log;

    public CustomerDTO mapToCustomerDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .username(customer.getUsername())
                .hashedPassword(customer.getHashedPassword())
                .passportNumber(customer.getPassportNumber())
                .build();
    }

    public CustomerDTO mapToCustomerDto(Optional<Customer> optCustomer) {

        if (optCustomer.isEmpty()) {
            log.error("Customer not found");
            return null;
        }
        Customer customer = optCustomer.get();
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

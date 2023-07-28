package com.example.practiceOne.utils.mappers;

import com.example.practiceOne.entities.customer.Customer;
import com.example.practiceOne.entities.customer.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerMapper {

    private final Logger log;

    public CustomerDTO mapToCustomerDto(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .passportNumber(customer.getPassportNumber())
                .balance(customer.getBalance())
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
                .password(customer.getPassword())
                .passportNumber(customer.getPassportNumber())
                .balance(customer.getBalance())
                .build();
    }



    public Customer mapToCustomer(CustomerDTO dto) {
        return Customer.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .passportNumber(dto.getPassportNumber())
                .balance(dto.getBalance())
                .build();
    }
}

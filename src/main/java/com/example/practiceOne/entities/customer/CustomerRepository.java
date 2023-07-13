package com.example.practiceOne.entities.customer;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepository{

    private List<CustomerDTO> customers;

    public CustomerRepository() {
        customers = new ArrayList<>();
        customers.add(CustomerDTO.builder().id(1L).username("tamerlan").email("tamer@gmail.com").build());
        customers.add(CustomerDTO.builder().id(2L).username("alisher").email("alish@gmail.com").build());
        customers.add(CustomerDTO.builder().id(3L).username("string").email("string@gmail.com").build());
    }

    public CustomerDTO getCustomerById(Long id) {
        for (CustomerDTO customer : customers) {
            if (Objects.equals(customer.getId(), id)) {
                return customer;
            }
        }
        return null;
    }

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

}

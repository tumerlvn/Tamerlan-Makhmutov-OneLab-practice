package com.example.practiceOne.repository;

import com.example.practiceOne.entities.customer.Customer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Customer getCustomerById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM customers WHERE id = ?",
                new Object[]{id},
                (rs, rowNum) -> new Customer(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("hashedPassword"),
                        rs.getString("passportNumber")
                )
        );
    }

    public List<Customer> getCustomers() {
        return jdbcTemplate.query(
                "SELECT * FROM customers",
                (rs, rowNum) -> new Customer(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("hashedPassword"),
                        rs.getString("passportNumber")
                )
        );
    }


}

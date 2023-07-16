package com.example.practiceOne.entities.customer;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="customers")
@Data
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String hashedPassword;

    @Column
    private String passportNumber;


}

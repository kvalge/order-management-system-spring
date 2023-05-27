package com.example.ordermanagementsystemspring.domain.customer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String registrationCode;

    @Column(length = 250, unique = true, nullable = false)
    private String full_name;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 50, unique = true, nullable = false)
    private String telephone;
}

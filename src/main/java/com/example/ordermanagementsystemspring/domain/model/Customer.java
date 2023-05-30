package com.example.ordermanagementsystemspring.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identityGenerator")
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String registration_code;

    @Column(length = 250, nullable = false)
    private String full_name;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String telephone;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}

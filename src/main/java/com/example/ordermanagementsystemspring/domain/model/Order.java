package com.example.ordermanagementsystemspring.domain.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identityGenerator")
    private Long id;

    @Column(nullable = false)
    private Date submissionDate;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private OrderLine orderLine;
}

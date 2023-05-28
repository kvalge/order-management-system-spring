package com.example.ordermanagementsystemspring.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identityGenerator")
    private Long id;

    @Column(length = 250, nullable = false)
    private String name;

    @Column(length = 50, unique = true, nullable = false)
    private String skuCode;

    @Column(length = 250, nullable = false)
    private Integer unitPrice;
}



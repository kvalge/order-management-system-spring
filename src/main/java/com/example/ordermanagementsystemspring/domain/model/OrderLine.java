package com.example.ordermanagementsystemspring.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_line")
@Data
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identityGenerator")
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @OneToOne
    private Product product;
}

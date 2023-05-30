package com.example.ordermanagementsystemspring.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identityGenerator")
    private Long id;

    @Column
    private LocalDate submissionDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "order", orphanRemoval = true)
    private List<OrderLine> orderLines = new ArrayList<>();
}

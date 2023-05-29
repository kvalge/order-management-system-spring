package com.example.ordermanagementsystemspring.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identityGenerator")
    private Long id;

    @Column
    private Date submissionDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();
}

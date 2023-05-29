package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDto {

    private Long id;
    private LocalDate submissionDate;
    private Long customerId;
}

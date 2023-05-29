package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {

    private Long id;
    private Date submissionDate;
    private Long customerId;
    private Long orderLineId;
}

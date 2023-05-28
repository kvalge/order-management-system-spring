package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

@Data
public class OrderLineDto {

    private Long id;
    private Integer quantity;
    private Long productId;
}

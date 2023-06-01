package com.example.ordermanagementsystemspring.domain.service.dto;

import lombok.Data;

@Data
public class OrderLineRequest {

    private Integer quantity;
    private Long productId;
    private Long orderId;
}

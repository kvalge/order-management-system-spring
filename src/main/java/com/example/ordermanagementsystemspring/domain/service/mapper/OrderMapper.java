package com.example.ordermanagementsystemspring.domain.service.mapper;

import com.example.ordermanagementsystemspring.domain.model.Order;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderDto;
import com.example.ordermanagementsystemspring.domain.service.dto.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrderMapper extends EntityMapper<Order, OrderDto>{

    Order requestToOrder(OrderRequest request);

    @Override
    @Mapping(source = "customer.id", target = "customerId")
    OrderDto toDto(Order entity);
}

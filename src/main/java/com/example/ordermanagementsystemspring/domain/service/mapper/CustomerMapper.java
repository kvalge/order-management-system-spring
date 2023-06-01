package com.example.ordermanagementsystemspring.domain.service.mapper;

import com.example.ordermanagementsystemspring.domain.model.Customer;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerDto;
import com.example.ordermanagementsystemspring.domain.service.dto.CustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        imports = UUID.class)
public interface CustomerMapper extends EntityMapper<Customer, CustomerDto> {

    @Mapping(expression = "java(UUID.randomUUID().toString())", target = ("registrationCode"))
    Customer requestToEntity(CustomerRequest request);
}

package com.example.ordermanagementsystemspring.domain.customer;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerDto dto) {
        if (dto == null) {
            return null;
        }

        Customer entity = new Customer();
        entity.setRegistrationCode(UUID.randomUUID().toString());
        entity.setFull_name(dto.getFull_name());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());
        return entity;
    }

    public CustomerDto toDto(Customer entity) {
        if (entity == null) {
            return null;
        }

        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setRegistrationCode(entity.getRegistrationCode());
        dto.setFull_name(entity.getFull_name());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());
        return dto;
    }
}

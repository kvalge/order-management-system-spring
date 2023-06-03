package com.example.ordermanagementsystemspring.domain.service.mapper;

import com.example.ordermanagementsystemspring.domain.model.User;
import com.example.ordermanagementsystemspring.domain.service.dto.UserDto;
import com.example.ordermanagementsystemspring.domain.service.dto.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper extends EntityMapper<User, UserDto> {
    @Mapping(source = "customerId", target = "customer.id")
    User requestToEntity(UserRequest request);

    @Override
    @Mapping(source = "customer.id", target = "customerId")
    UserDto toDto(User entity);
}

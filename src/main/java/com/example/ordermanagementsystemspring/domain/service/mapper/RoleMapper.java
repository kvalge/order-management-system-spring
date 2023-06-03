package com.example.ordermanagementsystemspring.domain.service.mapper;

import com.example.ordermanagementsystemspring.domain.model.Role;
import com.example.ordermanagementsystemspring.domain.service.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RoleMapper extends EntityMapper<Role, RoleDto> {

    Role nameToEntity(String name);
}

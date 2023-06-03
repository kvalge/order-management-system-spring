package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Role;
import com.example.ordermanagementsystemspring.domain.repository.RoleRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.RoleDto;
import com.example.ordermanagementsystemspring.domain.service.mapper.RoleMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleRepository roleRepository;

    public RoleDto save(String name) {
        log.info("Request to save Role : {}", name);

        Role role = roleMapper.nameToEntity(name);
        role = roleRepository.save(role);

        return roleMapper.toDto(role);
    }
}

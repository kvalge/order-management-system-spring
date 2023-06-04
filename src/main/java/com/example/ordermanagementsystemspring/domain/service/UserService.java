package com.example.ordermanagementsystemspring.domain.service;

import com.example.ordermanagementsystemspring.domain.model.Role;
import com.example.ordermanagementsystemspring.domain.model.User;
import com.example.ordermanagementsystemspring.domain.repository.RoleRepository;
import com.example.ordermanagementsystemspring.domain.repository.UserRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.UserDto;
import com.example.ordermanagementsystemspring.domain.service.dto.UserRequest;
import com.example.ordermanagementsystemspring.domain.service.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    public UserDto save(UserRequest request) {
        User user = userMapper.requestToEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("USER");
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return userMapper.toDto(user);
    }
}

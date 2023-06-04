package com.example.ordermanagementsystemspring.domain.validation;

import com.example.ordermanagementsystemspring.domain.exception.UserException;
import com.example.ordermanagementsystemspring.domain.model.User;
import com.example.ordermanagementsystemspring.domain.repository.UserRepository;
import com.example.ordermanagementsystemspring.domain.service.dto.UserRequest;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserValidationService {

    @Resource
    private UserRepository userRepository;

    public void userAlreadyExists(UserRequest request) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (Objects.equals(user.getUsername(), request.getUsername())) {
                throw new UserException("Username " + request.getUsername() + " already exists!");
            }
        }
    }
}

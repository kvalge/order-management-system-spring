package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.UserService;
import com.example.ordermanagementsystemspring.domain.service.dto.UserDto;
import com.example.ordermanagementsystemspring.domain.service.dto.UserRequest;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping(value = "/register/user", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRequest request) {
        log.info("REST request to register User");

        return ResponseEntity
                .ok()
                .body(userService.save(request));
    }
}

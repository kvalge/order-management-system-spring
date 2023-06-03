package com.example.ordermanagementsystemspring.domain.controller;

import com.example.ordermanagementsystemspring.domain.service.RoleService;
import com.example.ordermanagementsystemspring.domain.service.dto.RoleDto;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping(value = "/role", produces = {"application/json"})
    public ResponseEntity<RoleDto> createRole(@RequestParam String name) {
        log.info("REST request to create Role");

        return ResponseEntity
                .ok()
                .body(roleService.save(name));
    }
}

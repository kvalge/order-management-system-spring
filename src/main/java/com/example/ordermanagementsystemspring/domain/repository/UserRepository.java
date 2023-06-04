package com.example.ordermanagementsystemspring.domain.repository;

import com.example.ordermanagementsystemspring.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}

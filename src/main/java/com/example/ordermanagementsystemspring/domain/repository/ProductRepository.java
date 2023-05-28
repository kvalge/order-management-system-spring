package com.example.ordermanagementsystemspring.domain.repository;

import com.example.ordermanagementsystemspring.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

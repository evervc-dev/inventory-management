package com.evervc.dev.inventorymanagement.repository;

import com.evervc.dev.inventorymanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

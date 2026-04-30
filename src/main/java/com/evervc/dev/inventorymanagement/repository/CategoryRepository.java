package com.evervc.dev.inventorymanagement.repository;

import com.evervc.dev.inventorymanagement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

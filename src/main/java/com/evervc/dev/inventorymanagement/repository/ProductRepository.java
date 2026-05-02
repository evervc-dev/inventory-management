package com.evervc.dev.inventorymanagement.repository;

import com.evervc.dev.inventorymanagement.entity.Category;
import com.evervc.dev.inventorymanagement.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByStockAfter(Integer stockAfter, Pageable pageable);

    Page<Product> findAllByStockBefore(Integer stockBefore, Pageable pageable);

    Page<Product> findAllByCategory(Category category, Pageable pageable);

    boolean existsBySku(String sku);
}
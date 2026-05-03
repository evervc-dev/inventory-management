package com.evervc.dev.inventorymanagement.repository;

import com.evervc.dev.inventorymanagement.entity.Category;
import com.evervc.dev.inventorymanagement.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByStockAfterAndActiveTrue(Integer stockAfter, Pageable pageable);

    Page<Product> findAllByStockBeforeAndActiveTrue(Integer stockBefore, Pageable pageable);

    Page<Product> findAllByCategoryAndActiveTrue(Category category, Pageable pageable);

    Optional<Product> findByIdAndActiveTrue(Long id);

    boolean existsBySku(String sku);
}
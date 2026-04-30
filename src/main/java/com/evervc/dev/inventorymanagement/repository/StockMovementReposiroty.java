package com.evervc.dev.inventorymanagement.repository;

import com.evervc.dev.inventorymanagement.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockMovementReposiroty extends JpaRepository<StockMovement, Long> {
}

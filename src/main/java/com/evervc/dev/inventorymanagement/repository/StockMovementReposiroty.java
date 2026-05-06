package com.evervc.dev.inventorymanagement.repository;

import com.evervc.dev.inventorymanagement.entity.StockMovement;
import com.evervc.dev.inventorymanagement.entity.User;
import com.evervc.dev.inventorymanagement.entity.enums.MovementType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockMovementReposiroty extends JpaRepository<StockMovement, Long> {

    Page<StockMovement> findAllByUser(User user, Pageable pageable);

    Page<StockMovement> findAllByTypeAndUser(MovementType type, User user, Pageable pageable);

    Optional<StockMovement> findByIdAndUser(User user, Long id);
}
package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.movement.MovementRequestDto;
import com.evervc.dev.inventorymanagement.entity.User;
import com.evervc.dev.inventorymanagement.entity.enums.MovementType;
import org.springframework.data.domain.Pageable;

public interface StockMovementService {

    BaseResponseDto findAll(Pageable pageable);

    BaseResponseDto finAllByUser(User user, Pageable pageable);

    BaseResponseDto findAllByTypeAndUser(MovementType  type, User user, Pageable pageable);

    BaseResponseDto findByIdAndUser(Long id, User user);

    BaseResponseDto findById(Long id);

    BaseResponseDto create(MovementRequestDto movementDto);

    void remove(Long id);
}

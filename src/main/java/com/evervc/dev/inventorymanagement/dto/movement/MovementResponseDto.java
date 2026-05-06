package com.evervc.dev.inventorymanagement.dto.movement;

import com.evervc.dev.inventorymanagement.dto.product.FullProductResponseDto;
import com.evervc.dev.inventorymanagement.dto.user.UserResponseDto;
import com.evervc.dev.inventorymanagement.entity.enums.MovementType;

import java.time.LocalDate;
import java.util.List;

public record MovementResponseDto(
        Long id,
        LocalDate date,
        List<FullProductResponseDto> products,
        UserResponseDto user,
        MovementType movementType
) {}

package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.dto.movement.MovementRequestDto;
import com.evervc.dev.inventorymanagement.dto.movement.MovementResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.FullProductResponseDto;
import com.evervc.dev.inventorymanagement.entity.StockMovement;
import com.evervc.dev.inventorymanagement.entity.enums.MovementType;

import java.util.List;

public class MovementMapper {

    public static StockMovement toEntity(MovementRequestDto dto) {
        return StockMovement.builder()
                .date(dto.date())
                .type(MovementType.valueOf(dto.movementType()))
                .build();
    }

    public static MovementResponseDto toDto(StockMovement movement) {
        List<FullProductResponseDto> products = movement.getProducts()
                .stream().map(ProductMapper::toFullDto).toList();
        return new MovementResponseDto(
                movement.getId(),
                movement.getDate(),
                products,
                UserMapper.toDto(movement.getUser()),
                movement.getType()
        );
    }
}

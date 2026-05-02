package com.evervc.dev.inventorymanagement.dto.product;

import java.math.BigDecimal;

public record ProductResponseDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Boolean active
) {}

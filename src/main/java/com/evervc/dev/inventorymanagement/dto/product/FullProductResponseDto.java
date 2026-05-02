package com.evervc.dev.inventorymanagement.dto.product;

import com.evervc.dev.inventorymanagement.dto.category.CategoryResponseDto;

import java.math.BigDecimal;

public record FullProductResponseDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Boolean active,
        CategoryResponseDto category
) {
}

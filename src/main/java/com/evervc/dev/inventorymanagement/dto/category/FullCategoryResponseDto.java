package com.evervc.dev.inventorymanagement.dto.category;

import com.evervc.dev.inventorymanagement.dto.product.ProductResponseDto;

import java.util.List;

public record FullCategoryResponseDto(
        Long id,
        String name,
        String description,
        List<ProductResponseDto> products
) {}

package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.dto.category.CategoryRequestDto;
import com.evervc.dev.inventorymanagement.dto.category.CategoryResponseDto;
import com.evervc.dev.inventorymanagement.dto.category.FullCategoryResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductResponseDto;
import com.evervc.dev.inventorymanagement.entity.Category;

import java.util.List;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDto dto) {
        return Category.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    public static CategoryResponseDto toSimpleDto(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    public static FullCategoryResponseDto toFullDto(Category category) {
        List<ProductResponseDto>  products = category.getProducts()
                .stream().map(ProductMapper::toSimpleDto).toList();
        return new FullCategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                products
        );
    }
}

package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.dto.product.FullProductResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductRequestDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductResponseDto;
import com.evervc.dev.inventorymanagement.entity.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequestDto dto) {
        return Product.builder()
                .name(dto.name())
                .sku(dto.sku())
                .description(dto.description())
                .price(dto.price())
                .stock(dto.stock())
                .build();
    }

    public static ProductResponseDto toSimpleDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getActive()
        );
    }

    public static FullProductResponseDto toFullDto(Product product) {
        return new FullProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getActive(),
                CategoryMapper.toSimpleDto(product.getCategory())
        );
    }
}

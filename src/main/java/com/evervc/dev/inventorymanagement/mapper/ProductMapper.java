package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.config.GlobalMapperConfiguration;
import com.evervc.dev.inventorymanagement.dto.category.CategoryResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.*;
import com.evervc.dev.inventorymanagement.entity.Category;
import com.evervc.dev.inventorymanagement.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = GlobalMapperConfiguration.class)
public interface ProductMapper {

    ProductResponseDto toResponseDto(Product product);

    FullProductResponseDto toFullResponseDto(Product product);

    List<ProductResponseDto> toResponseDtoList(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "category.id", source = "categoryId")
    Product toEntity(ProductCreateDto dto);

    // Actualizar (PUT): Sbreescribe completamente (excepto sku)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sku", ignore = true)
    @Mapping(target = "category.id", source = "categoryId")
    void updateEntityFromPutDto(ProductUpdateDto dto, @MappingTarget Product entity);

    // Actualizar (PATCH): Solo ciertos campos
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category.id", source = "categoryId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromPatchDto(ProductPatchDto dto, @MappingTarget Product entity);

    // Convertir categoría a un simple DTO
    CategoryResponseDto toCategoryResponseDto(Category category);

}

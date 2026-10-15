package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.config.GlobalMapperConfiguration;
import com.evervc.dev.inventorymanagement.dto.category.CategoryResponseDto;
import com.evervc.dev.inventorymanagement.dto.category.FullCategoryResponseDto;
import com.evervc.dev.inventorymanagement.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = GlobalMapperConfiguration.class)
public interface CategoryMapper {
    CategoryResponseDto toResponseDto(Category category);

    FullCategoryResponseDto toFullResponseDto(Category category);

    List<CategoryResponseDto> toResponseDtoList(List<Category> cateories);
}

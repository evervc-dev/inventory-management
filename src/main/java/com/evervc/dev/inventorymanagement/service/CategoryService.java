package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.category.CategoryRequestDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    BaseResponseDto findAll(Pageable pageable);

    BaseResponseDto findAllWithProducts(Pageable pageable);

    BaseResponseDto findById(Long id);

    BaseResponseDto findByIdWithProducts(Long id);

    BaseResponseDto findByName(String name);

    BaseResponseDto create(CategoryRequestDto categoryDto);

    void remove(Long id);
}

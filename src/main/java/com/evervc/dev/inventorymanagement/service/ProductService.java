package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductCreateDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductUpdateDto;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    BaseResponseDto findAll(Pageable pageable);

    BaseResponseDto findAllByCategory(Long categoryId, Pageable pageable);

    BaseResponseDto findById(Long id);

    BaseResponseDto create(ProductCreateDto productDto);

    BaseResponseDto replace(ProductUpdateDto productDto, Long id);

    void remove(Long id);
}

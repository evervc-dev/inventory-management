package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductRequestDto;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    BaseResponseDto findAll(Pageable pageable);

    BaseResponseDto findAllByCategoryId(long categoryId, Pageable pageable);

    BaseResponseDto findById(long id);

    BaseResponseDto save(ProductRequestDto productDto);

    void remove(long id);
}

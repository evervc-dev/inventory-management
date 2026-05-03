package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.FullProductResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductRequestDto;
import com.evervc.dev.inventorymanagement.entity.Category;
import com.evervc.dev.inventorymanagement.entity.Product;
import com.evervc.dev.inventorymanagement.exception.BusinessRuleException;
import com.evervc.dev.inventorymanagement.exception.ResourceNotFoundException;
import com.evervc.dev.inventorymanagement.mapper.ProductMapper;
import com.evervc.dev.inventorymanagement.repository.CategoryRepository;
import com.evervc.dev.inventorymanagement.repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final HttpServletRequest httpServletRequest;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findAll(Pageable pageable) {
        // Retorna solo los productos con Stock mayor a 0 (es decir, de 1 en adelante)
        Page<Product> products = productRepository.findAllByStockAfter(0, pageable);

        Page<FullProductResponseDto> productsPage = products.map(ProductMapper::toFullDto);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                productsPage
        );
    }

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findAllByCategoryId(long categoryId, Pageable pageable) {
        Category category = getCategory(categoryId);

        Page<Product> products = productRepository.findAllByCategory(category, pageable);

        Page<FullProductResponseDto> productsPage = products.map(ProductMapper::toFullDto);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                productsPage
        );
    }

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findById(long id) {
        Product product = getProduct(id);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                ProductMapper.toFullDto(product)
        );
    }

    @Transactional
    @Override
    public BaseResponseDto create(ProductRequestDto productDto) {
        Category category = getCategory(productDto.categoryId());

        if (productRepository.existsBySku(productDto.sku()))
            throw  new BusinessRuleException("Ya existe un producto con el SKU [" + productDto.sku() + "].");

        Product product = ProductMapper.toEntity(productDto);
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                ProductMapper.toFullDto(savedProduct)
        );
    }

    @Transactional
    @Override
    public void remove(long id) {
        Product product = getProduct(id);
        product.setActive(false);
        productRepository.save(product);
    }

    private Category getCategory(long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("La categoria con ID [" + id + "] no existe.")
        );
    }

    private Product getProduct(long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El producto con ID [" + id + "] no existe.")
        );
    }
}

package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.FullProductResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductCreateDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductPatchDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductUpdateDto;
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
        Page<Product> products = productRepository.findAllByStockAfterAndActiveTrue(0, pageable);

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
    public BaseResponseDto findAllByCategory(Long categoryId, Pageable pageable) {
        Category category = getCategory(categoryId);

        Page<Product> products = productRepository.findAllByCategoryAndActiveTrue(category, pageable);

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
    public BaseResponseDto findById(Long id) {
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
    public BaseResponseDto create(ProductCreateDto productDto) {
        Category category = getCategory(productDto.categoryId());

        if (productRepository.existsBySku(productDto.sku()))
            throw  new BusinessRuleException("Ya existe un producto con el SKU [" + productDto.sku() + "].");

        Product product = ProductMapper.toEntity(productDto);
        product.setCategory(category);

        Product productCreated = productRepository.save(product);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                ProductMapper.toFullDto(productCreated)
        );
    }

    @Transactional
    @Override
    public BaseResponseDto replace(ProductUpdateDto productDto, Long id) {
        Product product = getProduct(id);

        Category category = getCategory(productDto.categoryId());

        product.setName(productDto.name());
        product.setDescription(productDto.description());
        product.setPrice(productDto.price());
        product.setStock(productDto.stock());
        product.setActive(productDto.active());
        product.setCategory(category);

        Product productUpdated = productRepository.save(product);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                ProductMapper.toFullDto(productUpdated)
        );
    }

    @Override
    public BaseResponseDto update(ProductPatchDto productDto, Long id) {
        Product product = getProduct(id);

        // Verifica si hay cambios en los valores y actualiza esos cambios en el objeto de producto
        if (productDto.name() != null) {
            product.setName(productDto.name());
        }

        if (productDto.description() != null) {
            product.setDescription(productDto.description());
        }

        if (productDto.price() != null) {
            product.setPrice(productDto.price());
        }

        if (productDto.stock() != null) {
            product.setStock(productDto.stock());
        }

        if (productDto.active() != null) {
            product.setActive(productDto.active());
        }

        if (productDto.categoryId() != null) {
            Category category = getCategory(productDto.categoryId());

            product.setCategory(category);
        }

        Product productUpdated = productRepository.save(product);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                ProductMapper.toFullDto(productUpdated)
        );
    }

    @Transactional
    @Override
    public void remove(Long id) {
        Product product = getProduct(id);
        product.setActive(false);
        productRepository.save(product);
    }

    private Category getCategory(Long id) {
        return categoryRepository.findByIdAndActiveTrue(id).orElseThrow(
                () -> new ResourceNotFoundException("La categoria con ID [" + id + "] no existe.")
        );
    }

    private Product getProduct(Long id) {
        return productRepository.findByIdAndActiveTrue(id).orElseThrow(
                () -> new ResourceNotFoundException("El producto con ID [" + id + "] no existe.")
        );
    }
}

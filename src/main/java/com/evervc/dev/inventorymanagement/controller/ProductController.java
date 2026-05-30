package com.evervc.dev.inventorymanagement.controller;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductCreateDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductPatchDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductUpdateDto;
import com.evervc.dev.inventorymanagement.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<BaseResponseDto> getProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (categoryId != null) {
            return new ResponseEntity<>(productService.findAllByCategory(categoryId, PageRequest.of(page, size)), HttpStatus.OK);
        }
        return new ResponseEntity<>(productService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponseDto> createProduct(
            @Valid @RequestBody ProductCreateDto productDto) {
        return new ResponseEntity<>(productService.create(productDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDto> getProductById(@PathVariable long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> replaceProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateDto productDto
            ) {
        return new ResponseEntity<>(productService.replace(productDto, id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponseDto> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductPatchDto productDto
            ) {
        return new ResponseEntity<>(productService.update(productDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }
}

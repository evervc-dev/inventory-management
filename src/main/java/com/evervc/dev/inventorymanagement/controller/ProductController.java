package com.evervc.dev.inventorymanagement.controller;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.product.ProductRequestDto;
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

    @PostMapping
    public ResponseEntity<BaseResponseDto> addProduct(
            @Valid @RequestBody ProductRequestDto productDto) {
        return new ResponseEntity<>(productService.create(productDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BaseResponseDto> getAllProducts(
            @RequestParam(required = false) Long category_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (category_id != null) {
            return new ResponseEntity<>(productService.findAllByCategoryId(category_id, PageRequest.of(page, size)), HttpStatus.OK);
        }
        return new ResponseEntity<>(productService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDto> getProductById(@PathVariable long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }
}

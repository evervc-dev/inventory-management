package com.evervc.dev.inventorymanagement.controller;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.category.CategoryRequestDto;
import com.evervc.dev.inventorymanagement.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<BaseResponseDto> createCategory(
            @Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.create(categoryRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BaseResponseDto> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10")  int size
    ) {
        return new ResponseEntity<>(categoryService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDto> getCategory(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<BaseResponseDto> getCategoryWithProducts(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.findByIdWithProducts(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.remove(id);
        return ResponseEntity.noContent().build();
    }

}

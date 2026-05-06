package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.category.CategoryRequestDto;
import com.evervc.dev.inventorymanagement.dto.category.CategoryResponseDto;
import com.evervc.dev.inventorymanagement.dto.category.FullCategoryResponseDto;
import com.evervc.dev.inventorymanagement.entity.Category;
import com.evervc.dev.inventorymanagement.exception.BusinessRuleException;
import com.evervc.dev.inventorymanagement.exception.ResourceNotFoundException;
import com.evervc.dev.inventorymanagement.mapper.CategoryMapper;
import com.evervc.dev.inventorymanagement.repository.CategoryRepository;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final HttpServletRequest httpServletRequest;

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAllByActiveTrue(pageable);
        Page<CategoryResponseDto> categoryPage = categories.map(CategoryMapper::toSimpleDto);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                categoryPage
        );
    }

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findAllWithProducts(Pageable pageable) {
        // La siguiente línea probablemente de un error por el proxy de Spring Boot, si es el caso
        // únicamete se debe hacer el llamado a: categoryRepository.findAllByActiveTrueWithProducts(pageable);
        Page<Category> categories = categoryRepository.findAllByActiveTrue(pageable);
        Page<FullCategoryResponseDto> categoryPage = categories.map(CategoryMapper::toFullDto);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                categoryPage
        );
    }

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findById(Long id) {
        Category category = getCategoryById(id);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                CategoryMapper.toSimpleDto(category)
        );
    }

    @Override
    public BaseResponseDto findByIdWithProducts(Long id) {
        /*Category category = categoryRepository.findByIdAndActiveTrueWithProducts(id).orElseThrow(
                () -> new ResourceNotFoundException("La categoría con ID [" + id + "] no existe.")
        );*/
        Category category = getCategoryById(id);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                CategoryMapper.toFullDto(category)
        );
    }

    @Override
    public BaseResponseDto findByName(String name) {
        Category category = categoryRepository.findByNameAndActiveTrue(name).orElseThrow(
                () -> new ResourceNotFoundException("La categoría  [" + name + "] no existe.")
        );

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                CategoryMapper.toFullDto(category)
        );
    }

    @Override
    public BaseResponseDto create(CategoryRequestDto categoryDto) {
        if (categoryRepository.existsByName(categoryDto.name()))
            throw new BusinessRuleException("Ya existe una categoría con el nombre de [" + categoryDto.name() + "].");

        Category category = CategoryMapper.toEntity(categoryDto);

        Category savedCategory = categoryRepository.save(category);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                CategoryMapper.toSimpleDto(savedCategory)
        );
    }

    @Override
    public void remove(Long id) {
        Category category = getCategoryById(id);

        category.setActive(false);
        categoryRepository.save(category);
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findByIdAndActiveTrue(id).orElseThrow(
                () -> new ResourceNotFoundException("La categoría con ID [" + id + "] no existe.")
        );
    }
}

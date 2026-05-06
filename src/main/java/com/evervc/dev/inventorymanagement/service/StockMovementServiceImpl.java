package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.movement.MovementRequestDto;
import com.evervc.dev.inventorymanagement.dto.movement.MovementResponseDto;
import com.evervc.dev.inventorymanagement.entity.Product;
import com.evervc.dev.inventorymanagement.entity.StockMovement;
import com.evervc.dev.inventorymanagement.entity.User;
import com.evervc.dev.inventorymanagement.entity.enums.MovementType;
import com.evervc.dev.inventorymanagement.exception.ResourceNotFoundException;
import com.evervc.dev.inventorymanagement.mapper.MovementMapper;
import com.evervc.dev.inventorymanagement.repository.ProductRepository;
import com.evervc.dev.inventorymanagement.repository.StockMovementReposiroty;
import com.evervc.dev.inventorymanagement.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementReposiroty stockMovementReposiroty;
    private final UserRepository userRepository;
    private final ProductRepository  productRepository;
    private final HttpServletRequest httpServletRequest;

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findAll(Pageable pageable) {
        Page<StockMovement> movements = stockMovementReposiroty.findAll(pageable);

        Page<MovementResponseDto> movementsPage = movements.map(MovementMapper::toDto);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                movementsPage
        );
    }

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto finAllByUser(User user, Pageable pageable) {
        Page<StockMovement> movements = stockMovementReposiroty.findAllByUser(user, pageable);

        Page<MovementResponseDto> movementsPage = movements.map(MovementMapper::toDto);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                movementsPage
        );
    }

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findAllByTypeAndUser(MovementType type, User user, Pageable pageable) {
        Page<StockMovement> movements = stockMovementReposiroty.findAllByTypeAndUser(type, user, pageable);

        Page<MovementResponseDto> movementsPage = movements.map(MovementMapper::toDto);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                movementsPage
        );
    }

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findByIdAndUser(Long id, User user) {
        StockMovement stockMovement = stockMovementReposiroty.findByIdAndUser(user, id).orElseThrow(
                () -> new ResourceNotFoundException("El movimiento con ID [" + id + "] no existe.")
        );

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                MovementMapper.toDto(stockMovement)
        );
    }

    @Transactional(readOnly = true)
    @Override
    public BaseResponseDto findById(Long id) {
        StockMovement stockMovement = stockMovementReposiroty.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El movimiento con ID [" + id + "] no existe.")
        );

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                MovementMapper.toDto(stockMovement)
        );
    }

    @Override
    public BaseResponseDto create(MovementRequestDto movementDto) {
        User user = getUserById(movementDto.userId());

        List<Product> products = new ArrayList<>();

        movementDto.productsIds().forEach(id -> {
            products.add(getProductById(id));
        });

        StockMovement stockMovement = MovementMapper.toEntity(movementDto);
        stockMovement.setUser(user);
        stockMovement.setProducts(products);

        StockMovement movementSaved = stockMovementReposiroty.save(stockMovement);

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                movementSaved
        );
    }

    @Override
    public void remove(Long id) {

    }

    private User getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new  ResourceNotFoundException("El usuario con el ID [" + id + "] no existe.")
        );
    }

    private Product getProductById(Long id) throws ResourceNotFoundException {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("El producto con ID [" + id + "] no existe.")
        );
    }
}

package com.evervc.dev.inventorymanagement.dto.product;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductPatchDto(

        String name,

        String description,

        @Positive(message = "El precio debe ser mayor a 0.")
        BigDecimal price,

        @PositiveOrZero(message = "El stock no puede ser negativo.")
        Integer stock,

        Boolean active,

        @Positive(message = "No se permiten valores negativos.")
        Long categoryId

) {}

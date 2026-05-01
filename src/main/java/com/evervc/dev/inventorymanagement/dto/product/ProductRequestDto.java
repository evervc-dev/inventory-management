package com.evervc.dev.inventorymanagement.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequestDto(
        @NotBlank(message = "El campo de nombre es obligatorio.")
        String name,

        @NotBlank(message = "El campo de sku es obligatorio.")
        String sku,

        @NotBlank(message = "El campo de descripción es obligatorio.")
        String description,

        @NotNull(message = "El campo de precio es obligatorio.")
        BigDecimal price,

        @NotNull(message = "El campo de stock es obligatorio.")
        @PositiveOrZero(message = "El stock no puede ser negativo.")
        Integer stock,

        @NotNull(message = "El ID de la categoría es obligatorio.")
        @Positive(message = "No se permiten valores negativos.")
        Long categoryId
) {}

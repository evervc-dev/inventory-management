package com.evervc.dev.inventorymanagement.dto.movement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record MovementRequestDto(
        @NotNull(message = "Debe indicar al menos un ID de producto para realizar un movimiento.")
        List<Long> productsIds,

        @NotNull(message = "El ID del usuario es obligatorio.")
        @Positive(message = "No se permiten valores negativos.")
        Long userId,

        @NotBlank(message = "El campo tipo de movimiento es obligatorio.")
        @Pattern(regexp = "IN|OUT", message = "Los únicos valores permitidos son: IN/OUT.")
        String movementType
) {}

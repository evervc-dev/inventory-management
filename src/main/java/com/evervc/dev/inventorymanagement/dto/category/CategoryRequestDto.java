package com.evervc.dev.inventorymanagement.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
        @NotBlank(message = "El campo de nombre es obligatorio.")
        String name,

        String description
) {}

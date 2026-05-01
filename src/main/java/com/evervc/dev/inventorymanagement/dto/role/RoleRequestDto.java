package com.evervc.dev.inventorymanagement.dto.role;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDto(
        @NotBlank(message = "El campo name es obligatorio.")
        String name
) {}

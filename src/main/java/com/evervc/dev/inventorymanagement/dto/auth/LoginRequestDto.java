package com.evervc.dev.inventorymanagement.dto.auth;

import com.evervc.dev.inventorymanagement.validation.EmailRegex;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "El campo de email es obligatorio.")
        @Min(value = 10, message = "El correo debe tener una extensión mínima de 10 caracteres")
        @EmailRegex
        String email,

        @NotBlank(message = "El campo de contraseña es obligatorio.")
        String password
) {}

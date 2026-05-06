package com.evervc.dev.inventorymanagement.dto.user;

import com.evervc.dev.inventorymanagement.validation.EmailRegex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRequestDto(
        @NotBlank(message = "El campo de nombre es obligatorio.")
        String firstName,

        @NotBlank(message = "El campo de apellido es obligatorio.")
        String lastName,

        String address,

        // Tarea: Crear una validación personalizada para el formato
        LocalDate birthDate,

        @NotBlank(message = "El campo de email es obligatorio.")
        @Size(min = 10, message = "El correo debe tener una extensión mínima de 10 caracteres")
        @EmailRegex
        String email,

        @NotBlank(message = "El campo de contraseña es obligatorio.")
        String password,

        Boolean isAdmin
) {
}

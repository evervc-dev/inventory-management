package com.evervc.dev.inventorymanagement.dto.user;

import com.evervc.dev.inventorymanagement.validation.EmailRegex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
        @Size(min = 8, max = 128, message = "La contraseña debe tener al menos 8 caracteres")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "La contraseña debe contener al menos una mayúscula, una minúscula, un dígito, y un carácter especial"
        )
        String password,

        Boolean isAdmin
) {}

package com.evervc.dev.inventorymanagement.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDto(
        @NotBlank(message = "El campo del refresh token es obligatorio")
        String refreshToken
) {}

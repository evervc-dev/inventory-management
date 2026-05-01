package com.evervc.dev.inventorymanagement.dto.auth;

public record AuthResponseDto(
        String message,
        String token,
        String refreshToken
) {}

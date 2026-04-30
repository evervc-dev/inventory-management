package com.evervc.dev.inventorymanagement.dto.auth;

import java.time.LocalDateTime;

public record AuthResponseDto(
        LocalDateTime timestamp,
        String message,
        String token,
        String refreshToken
) {}

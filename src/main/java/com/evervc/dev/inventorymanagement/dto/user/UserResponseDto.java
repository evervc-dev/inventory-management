package com.evervc.dev.inventorymanagement.dto.user;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String address,
        LocalDateTime birthDate
) {}

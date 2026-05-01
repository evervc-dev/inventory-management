package com.evervc.dev.inventorymanagement.dto.user;

import java.time.LocalDate;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String address,
        LocalDate birthDate,
        String email
) {}

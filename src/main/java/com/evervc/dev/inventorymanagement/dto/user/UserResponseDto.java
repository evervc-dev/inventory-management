package com.evervc.dev.inventorymanagement.dto.user;

import com.evervc.dev.inventorymanagement.dto.role.RoleResponseDto;

import java.time.LocalDate;
import java.util.List;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String address,
        LocalDate birthDate,
        String email,
        List<RoleResponseDto> roles
) {}

package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.dto.role.RoleRequestDto;
import com.evervc.dev.inventorymanagement.dto.role.RoleResponseDto;
import com.evervc.dev.inventorymanagement.entity.Role;

public class RoleMapper {

    public static Role toEntity(RoleRequestDto dto) {
        return Role.builder()
                .name(dto.name())
                .build();
    }

    public static RoleResponseDto toDto(Role role) {
        return new RoleResponseDto(
                role.getId(),
                role.getName()
        );
    }
}

package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.dto.role.RoleResponseDto;
import com.evervc.dev.inventorymanagement.dto.user.UserRequestDto;
import com.evervc.dev.inventorymanagement.dto.user.UserResponseDto;
import com.evervc.dev.inventorymanagement.entity.User;

import java.util.List;

public class UserMapperOld {

    public static User toEntity(UserRequestDto dto) {
        return User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .address(dto.address())
                .birthDate(dto.birthDate())
                .email(dto.email())
                .password(dto.password())
                .enabled(true)
                .build();
    }

    public static UserResponseDto toDto(User user) {
        List<RoleResponseDto> roles = user.getRoles().stream().map(RoleMapperOld::toDto).toList();
        return new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getAddress(),
                user.getBirthDate(),
                user.getEmail(),
                roles
        );
    }
}

package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.dto.role.RoleRequestDto;
import com.evervc.dev.inventorymanagement.dto.role.RoleResponseDto;
import com.evervc.dev.inventorymanagement.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class RoleMapperTest {

    private RoleMapper roleMapper;

    @BeforeEach
    void setUp() {
        roleMapper = Mappers.getMapper(RoleMapper.class);
    }

    // Aplicando patrón AAA para cada test
    @Test
    @DisplayName("Mapping role from Entity to DTO")
    void shouldMapEntityToDto() {

        // Arrange (Organizar)
        RoleResponseDto result = new RoleResponseDto(
                1L,
                "ADMIN"
        );

        // Act (Actuar)
        RoleResponseDto responseDto = roleMapper.toResponseDto(new Role(1L, "ADMIN"));

        // Assert (Afirmar con AsserJ)
        Assertions.assertEquals(result, responseDto);
    }
}

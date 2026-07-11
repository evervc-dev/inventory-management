package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.dto.role.RoleRequestDto;
import com.evervc.dev.inventorymanagement.dto.role.RoleResponseDto;
import com.evervc.dev.inventorymanagement.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

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

    @Test
    @DisplayName("Mapping role from DTO to Entity")
    void shouldMapDtoToEntity() {
        // Arrange
        Role result = new Role(null, "ADMIN");

        // Act
        Role role = roleMapper.toCreateEntity(new RoleRequestDto("ADMIN"));

        // Assert
        Assertions.assertEquals(result, role);
    }

    @Test
    @DisplayName("Mapping a list of roles to roles response DTO")
    void shouldMapRolesToListOfRolesDto() {
        // Arrange
        List<Role> roles = Arrays.asList(new Role(1L, "ADMIN"), new Role(2L, "USER"));
        List<RoleResponseDto> expectedDtos = Arrays.asList(
                new RoleResponseDto(1L, "ADMIN"),
                new RoleResponseDto(2L, "USER")
        );

        // Act
        List<RoleResponseDto> responseDtos = roleMapper.toRolesDto(roles);

        // Assert
        Assertions.assertEquals(expectedDtos, responseDtos);
    }
}

package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.config.GlobalMapperConfiguration;
import com.evervc.dev.inventorymanagement.dto.role.RoleRequestDto;
import com.evervc.dev.inventorymanagement.dto.role.RoleResponseDto;
import com.evervc.dev.inventorymanagement.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = GlobalMapperConfiguration.class)
public interface RoleMapper {
    // Para respuesta del servidor en DTO
    RoleResponseDto toResponseDto(Role role);

    // Para convertir cada rol de usuario a DTO
    List<RoleResponseDto> toRolesDto(List<Role> roles);

    // Para solicitud de creación del rol
    @Mapping(target = "id", ignore = true)
    Role toCreateEntity(RoleRequestDto dto);

}

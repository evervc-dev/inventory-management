package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.config.GlobalMapperConfiguration;
import com.evervc.dev.inventorymanagement.dto.user.UserRequestDto;
import com.evervc.dev.inventorymanagement.dto.user.UserResponseDto;
import com.evervc.dev.inventorymanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfiguration.class, uses = { RoleMapper.class })
public interface UserMapper {

    UserResponseDto toResponseDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    User toEntityFromCreate(UserRequestDto dto);
}

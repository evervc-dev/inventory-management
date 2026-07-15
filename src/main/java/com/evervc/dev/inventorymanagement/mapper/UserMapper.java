package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.config.GlobalMapperConfiguration;
import com.evervc.dev.inventorymanagement.dto.user.UserRequestDto;
import com.evervc.dev.inventorymanagement.dto.user.UserResponseDto;
import com.evervc.dev.inventorymanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = GlobalMapperConfiguration.class, uses = { RoleMapper.class })
public interface UserMapper {

    UserResponseDto toResponseDto(User user);

    List<UserResponseDto> toResponseDtoList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntityFromCreate(UserRequestDto dto);
}

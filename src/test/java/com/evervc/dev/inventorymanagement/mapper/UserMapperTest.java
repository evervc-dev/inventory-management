package com.evervc.dev.inventorymanagement.mapper;

import com.evervc.dev.inventorymanagement.dto.role.RoleResponseDto;
import com.evervc.dev.inventorymanagement.dto.user.UserRequestDto;
import com.evervc.dev.inventorymanagement.dto.user.UserResponseDto;
import com.evervc.dev.inventorymanagement.entity.Role;
import com.evervc.dev.inventorymanagement.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        RoleMapper roleMapper = new RoleMapperImpl();
        userMapper = new UserMapperImpl(roleMapper);
    }

    @Test
    @DisplayName("Mapping user from Entity to DTO")
    void shouldMapEntityToDto() {
        // Arrange
        List<Role> userRoles = List.of(new Role(1L, "ADMIN"), new Role(2L, "USER"));
        List<RoleResponseDto> expectedRoleDtos = List.of(new RoleResponseDto(1L, "ADMIN"), new RoleResponseDto(2L, "USER"));

        User expectedUser = User.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Perez")
                .address("El Salvador")
                .birthDate(LocalDate.of(2026, Month.JULY, 14))
                .email("juan.perez@gmail.com")
                .roles(userRoles)
                .build();

        UserResponseDto expectedDto = new UserResponseDto(
                1L,
                "Juan",
                "Perez",
                "El Salvador",
                LocalDate.of(2026, Month.JULY, 14),
                "juan.perez@gmail.com",
                expectedRoleDtos
        );

        // Act
        UserResponseDto actualDto = userMapper.toResponseDto(expectedUser);

        // Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    @DisplayName("Try to mapping a null user from Entity to DTO")
    void shouldReturnNullWhenMappingNullUser() {
        // Arrange, Act & Assert
        assertNull(userMapper.toResponseDto(null));
    }

    @Test
    @DisplayName("Mapping user from Entity to DTO with null optional fields")
    void shouldMapUserWithNullFieldsToDto() {
        // Arrange
        User userWithNulls = User.builder()
                .id(2L)
                .firstName("Ana")
                .lastName("Gomez")
                .email("ana.gomez@example.com")
                .password("password") // Password is not mapped to DTO, but required for entity
                .enabled(true) // Enabled is not mapped to DTO, but required for entity
                .address(null)
                .birthDate(null)
                .roles(null)
                .build();

        UserResponseDto expectedDto = new UserResponseDto(
                2L,
                "Ana",
                "Gomez",
                null,
                null,
                "ana.gomez@example.com",
                null
        );

        // Act
        UserResponseDto actualDto = userMapper.toResponseDto(userWithNulls);

        // Assert
        assertEquals(expectedDto, actualDto);
    }

    @Test
    @DisplayName("Mapping a list of users from Entities to DTOs")
    void shouldMapListOfEntitiesToDtoList() {
        // Arrange
        List<Role> user1Roles = List.of(new Role(1L, "ADMIN"));
        List<Role> user2Roles = List.of(new Role(2L, "USER"));

        User user1 = User.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Perez")
                .email("juan.perez@gmail.com")
                .roles(user1Roles)
                .build();

        User user2 = User.builder()
                .id(2L)
                .firstName("Ana")
                .lastName("Gomez")
                .email("ana.gomez@example.com")
                .roles(user2Roles)
                .build();

        List<User> users = Arrays.asList(user1, user2);

        List<UserResponseDto> expectedDtos = Arrays.asList(
                new UserResponseDto(1L, "Juan", "Perez", null, null, "juan.perez@gmail.com", List.of(new RoleResponseDto(1L, "ADMIN"))),
                new UserResponseDto(2L, "Ana", "Gomez", null, null, "ana.gomez@example.com", List.of(new RoleResponseDto(2L, "USER")))
        );

        // Act
        List<UserResponseDto> actualDtos = userMapper.toResponseDtoList(users);

        // Assert
        assertEquals(expectedDtos.size(), actualDtos.size());
        assertTrue(actualDtos.containsAll(expectedDtos) && expectedDtos.containsAll(actualDtos));
    }

    @Test
    @DisplayName("Try to map a null list of users to a null list of DTOs")
    void shouldReturnNullWhenMappingNullUserList() {
        // Arrange, Act & Assert
        assertNull(userMapper.toResponseDtoList(null));
    }

    @Test
    @DisplayName("Try to map a list of users with null elements to a list of DTOs with null elements")
    void shouldMapUserListWithNullElementsToDtoList() {
        // Arrange
        List<Role> userRoles = List.of(new Role(1L, "ADMIN"));
        User user = User.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Perez")
                .email("juan.perez@gmail.com")
                .roles(userRoles)
                .build();

        List<User> users = Arrays.asList(user, null);
        List<UserResponseDto> expectedDtos = Arrays.asList(
                new UserResponseDto(1L, "Juan", "Perez", null, null, "juan.perez@gmail.com", List.of(new RoleResponseDto(1L, "ADMIN"))),
                null
        );

        // Act
        List<UserResponseDto> actualDtos = userMapper.toResponseDtoList(users);

        // Assert
        assertEquals(expectedDtos.size(), actualDtos.size());
        // Custom assertion for lists with nulls, as containsAll might not work as expected
        for (int i = 0; i < expectedDtos.size(); i++) {
            assertEquals(expectedDtos.get(i), actualDtos.get(i));
        }
    }

    @Test
    @DisplayName("Mapping UserRequestDto to User Entity")
    void shouldMapUserRequestDtoToEntity() {
        // Arrange
        UserRequestDto requestDto = new UserRequestDto(
                "Carlos",
                "Ruiz",
                "Guatemala",
                LocalDate.of(1990, Month.JANUARY, 1),
                "carlos.ruiz@example.com",
                "securePassword123",
                false
        );

        User expectedEntity = User.builder()
                .firstName("Carlos")
                .lastName("Ruiz")
                .address("Guatemala")
                .birthDate(LocalDate.of(1990, Month.JANUARY, 1))
                .email("carlos.ruiz@example.com")
                .password("securePassword123")
                .enabled(true) // Defaulted by @Mapping(target = "enabled", constant = "true")
                .id(null) // Ignored by @Mapping(target = "id", ignore = true)
                .roles(null) // Ignored by @Mapping(target = "roles", ignore = true)
                .build();

        // Act
        User actualEntity = userMapper.toEntityFromCreate(requestDto);

        // Assert
        assertEquals(expectedEntity.getFirstName(), actualEntity.getFirstName());
        assertEquals(expectedEntity.getLastName(), actualEntity.getLastName());
        assertEquals(expectedEntity.getAddress(), actualEntity.getAddress());
        assertEquals(expectedEntity.getBirthDate(), actualEntity.getBirthDate());
        assertEquals(expectedEntity.getEmail(), actualEntity.getEmail());
        assertEquals(expectedEntity.getPassword(), actualEntity.getPassword());
        assertEquals(expectedEntity.getEnabled(), actualEntity.getEnabled());
        assertNull(actualEntity.getId()); // Should be ignored
        assertNull(actualEntity.getRoles()); // Should be ignored
    }

    @Test
    @DisplayName("Try to mapping a null UserRequestDto to User Entity")
    void shouldReturnNullWhenMappingNullUserRequestDto() {
        // Arrange, Act & Assert
        assertNull(userMapper.toEntityFromCreate(null));
    }

    @Test
    @DisplayName("Mapping UserRequestDto with null fields to User Entity")
    void shouldMapUserRequestDtoWithNullFieldsToEntity() {
        // Arrange
        UserRequestDto requestDtoWithNulls = new UserRequestDto(
                "Pedro",
                "Lopez",
                null, // address is null
                null, // birthDate is null
                "pedro.lopez@example.com",
                "anotherPassword",
                false
        );

        User expectedEntity = User.builder()
                .firstName("Pedro")
                .lastName("Lopez")
                .address(null)
                .birthDate(null)
                .email("pedro.lopez@example.com")
                .password("anotherPassword")
                .enabled(true)
                .id(null)
                .roles(null)
                .build();

        // Act
        User actualEntity = userMapper.toEntityFromCreate(requestDtoWithNulls);

        // Assert
        assertEquals(expectedEntity.getFirstName(), actualEntity.getFirstName());
        assertEquals(expectedEntity.getLastName(), actualEntity.getLastName());
        assertNull(actualEntity.getAddress());
        assertNull(actualEntity.getBirthDate());
        assertEquals(expectedEntity.getEmail(), actualEntity.getEmail());
        assertEquals(expectedEntity.getPassword(), actualEntity.getPassword());
        assertEquals(expectedEntity.getEnabled(), actualEntity.getEnabled());
        assertNull(actualEntity.getId());
        assertNull(actualEntity.getRoles());
    }
}
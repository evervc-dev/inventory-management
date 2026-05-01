package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.auth.AuthResponseDto;
import com.evervc.dev.inventorymanagement.dto.auth.LoginRequestDto;
import com.evervc.dev.inventorymanagement.dto.user.UserRequestDto;
import com.evervc.dev.inventorymanagement.entity.Role;
import com.evervc.dev.inventorymanagement.entity.User;
import com.evervc.dev.inventorymanagement.exception.BusinessRuleException;
import com.evervc.dev.inventorymanagement.exception.ResourceNotFoundException;
import com.evervc.dev.inventorymanagement.exception.TokenInvalidException;
import com.evervc.dev.inventorymanagement.mapper.UserMapper;
import com.evervc.dev.inventorymanagement.repository.RoleRepository;
import com.evervc.dev.inventorymanagement.repository.UserRepository;
import com.evervc.dev.inventorymanagement.security.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final HttpServletRequest httpServletRequest;

    @Transactional
    public BaseResponseDto register(UserRequestDto userDto) {
        if (userRepository.existsByEmail(userDto.email()))
            throw new BusinessRuleException("El correo " + userDto.email() + " ya esta registrado.");

        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.password()));

        List<Role> roles = new ArrayList<>();

        addRole(roles, "USER");

        if (userDto.isAdmin() != null && userDto.isAdmin())
            addRole(roles, "ADMIN");

        user.setRoles(roles);

        User userSaved = userRepository.save(user);
        String token = generateTokenWithExtraClaims(userSaved);
        String refreshToken = jwtUtils.generateRefreshToken(userSaved);

        AuthResponseDto body = new AuthResponseDto(
                "Usuario " + userSaved.getFirstName() + " registrado con exito.",
                token,
                refreshToken
        );

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                body
        );
    }

    @Transactional(readOnly = true)
    public BaseResponseDto login(LoginRequestDto requestDto) {
        Authentication manager = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password())
        );

        User user = (User) manager.getPrincipal();

        String token = generateTokenWithExtraClaims(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);

        AuthResponseDto body = new AuthResponseDto(
                "Login exitoso, bienvenido " + user.getFirstName(),
                token,
                refreshToken
        );

        return new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                body
        );
    }

    public BaseResponseDto refreshToken(String refreshToken) {
        String email = jwtUtils.getEmailFromToken(refreshToken);

        if (email != null) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("El email que indica no existe en la base de datos"));

            if (jwtUtils.isTokenValid(refreshToken, user)) {
                String token = generateTokenWithExtraClaims(user);
                String newRefreshToken = jwtUtils.generateRefreshToken(user);

                AuthResponseDto body = new AuthResponseDto(
                        "Token renovado exitosamente",
                        token,
                        newRefreshToken
                );

                return new BaseResponseDto(
                        LocalDateTime.now(),
                        HttpServletResponse.SC_OK,
                        httpServletRequest.getRequestURI(),
                        body
                );
            }
        }
        throw new TokenInvalidException("El refresh token no es válido o ha expirado.");
    }

    @Transactional(readOnly = true)
    protected void addRole(List<Role> roles, String roleName) {
        Role role = roleRepository.findByName(roleName).orElseThrow(
                () -> new ResourceNotFoundException("El rol " + roleName + " no existe en la base de datos.")
        );
        roles.add(role);
    }

    private String generateTokenWithExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("userId", user.getId());
        extraClaims.put("fullName", String.join(" ", user.getFirstName(), user.getLastName()));

        // Mapea los roles
        List<String>  roles = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        extraClaims.put("roles", roles);

        return jwtUtils.generateToken(extraClaims, user);
    }
}

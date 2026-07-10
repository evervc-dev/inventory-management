package com.evervc.dev.inventorymanagement.controller;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.entity.User;
import com.evervc.dev.inventorymanagement.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<BaseResponseDto> getUserAuthenticated(@AuthenticationPrincipal User user, HttpServletRequest httpServletRequest) {
        BaseResponseDto response = new BaseResponseDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_OK,
                httpServletRequest.getRequestURI(),
                userMapper.toResponseDto(user)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

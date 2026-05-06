package com.evervc.dev.inventorymanagement.controller;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.auth.LoginRequestDto;
import com.evervc.dev.inventorymanagement.dto.auth.RefreshTokenRequestDto;
import com.evervc.dev.inventorymanagement.dto.user.UserRequestDto;
import com.evervc.dev.inventorymanagement.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDto> register(@Valid @RequestBody UserRequestDto userDto) {
        return new ResponseEntity<>(authenticationService.register(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDto> login(@Valid @RequestBody LoginRequestDto loginDto) {
        return new ResponseEntity<>(authenticationService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<BaseResponseDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshTokenDto) {
        return new ResponseEntity<>(authenticationService.refreshToken(refreshTokenDto.refreshToken()), HttpStatus.OK);
    }
}

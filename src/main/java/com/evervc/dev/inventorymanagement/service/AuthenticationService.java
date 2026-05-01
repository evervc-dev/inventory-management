package com.evervc.dev.inventorymanagement.service;

import com.evervc.dev.inventorymanagement.dto.BaseResponseDto;
import com.evervc.dev.inventorymanagement.dto.auth.LoginRequestDto;
import com.evervc.dev.inventorymanagement.dto.user.UserRequestDto;

public interface AuthenticationService {
    BaseResponseDto register(UserRequestDto userDto);
    BaseResponseDto login(LoginRequestDto requestDto);
    BaseResponseDto refreshToken(String refreshToken);
}
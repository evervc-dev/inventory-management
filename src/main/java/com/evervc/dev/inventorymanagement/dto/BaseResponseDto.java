package com.evervc.dev.inventorymanagement.dto;

import java.time.LocalDateTime;

public record BaseResponseDto(
        LocalDateTime timestamp,
        int status,
        String path,
        Object data
) {}

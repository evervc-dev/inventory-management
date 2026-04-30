package com.evervc.dev.inventorymanagement.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorDto(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        Map<String, String> details,
        String path
) {}

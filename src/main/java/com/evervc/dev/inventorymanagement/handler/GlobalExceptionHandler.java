package com.evervc.dev.inventorymanagement.handler;

import com.evervc.dev.inventorymanagement.dto.ErrorDto;
import com.evervc.dev.inventorymanagement.exception.BusinessRuleException;
import com.evervc.dev.inventorymanagement.exception.ResourceNotFoundException;
import com.evervc.dev.inventorymanagement.exception.TokenInvalidException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    // Validación de los DTO (HTTP 400 - Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> validationsHandler(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            details.put(field, message);
        });

        ErrorDto response = new ErrorDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_BAD_REQUEST,
                "Bad Request",
                "Los valores de los campos enviados no son válidos.",
                details,
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //Usuario no encontrado en la base de datos (HTTP 404 - Not Found)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDto> userNotFoundHandler(UsernameNotFoundException ex, HttpServletRequest request) {
        ErrorDto response = new ErrorDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_NOT_FOUND,
                "Resource Not Found",
                ex.getMessage(),
                null,
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //Recurso no encontrado en la base de datos (HTTP 404 - Not Found)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFoundHandler(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorDto response = new ErrorDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_NOT_FOUND,
                "Resource Not Found",
                ex.getMessage(),
                null,
                request.getRequestURI()
                );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Regla de negocio (HTTP 409 - Conflict)
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorDto> businessRuleHandler(BusinessRuleException ex, HttpServletRequest request) {
        ErrorDto response = new ErrorDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_CONFLICT,
                "Conflict",
                ex.getMessage(),
                null,
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    // Credenciales incorrectas (HTTP 400 - Bad Request)
    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<ErrorDto> invalidTokenHandler(TokenInvalidException ex, HttpServletRequest request) {
        ErrorDto response = new ErrorDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                ex.getMessage(),
                null,
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Credenciales incorrectas (HTTP 400 - Bad Request)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> badCredentialsHandler(BadCredentialsException ex, HttpServletRequest request) {
        ErrorDto response = new ErrorDto(
                LocalDateTime.now(),
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                "El correo o la contraseña son incorrectos. Por favor verifique sus credenciales.",
                null,
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}

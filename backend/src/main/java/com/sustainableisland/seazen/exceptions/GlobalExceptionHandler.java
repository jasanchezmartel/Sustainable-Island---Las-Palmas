package com.sustainableisland.seazen.exceptions;

import com.sustainableisland.seazen.dtos.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFound(
            UsernameNotFoundException ex, WebRequest request) {
        return ApiResponse.error(
                "User not found: " + ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentials(
            BadCredentialsException ex, WebRequest request) {
        return ApiResponse.error(
                "Email or password incorrect",
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFound(
            EntityNotFoundException ex, WebRequest request) {
        return ApiResponse.error(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDenied(
            AccessDeniedException ex, WebRequest request) {
        return ApiResponse.error(
                "Access denied",
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<Void>> handleExpiredJwt(
            ExpiredJwtException ex, WebRequest request) {
        return ApiResponse.error(
                "Token expired",
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidJwt(
            SignatureException ex, WebRequest request) {
        return ApiResponse.error(
                "Token invalid",
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(
                new ApiResponse<>(
                        false,
                        "Validation error",
                        errors,
                        java.time.LocalDateTime.now(),
                        request.getDescription(false).replace("uri=", ""),
                        HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(
            Exception ex, WebRequest request) {
        return ApiResponse.error(
                "Internal server error: " + ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
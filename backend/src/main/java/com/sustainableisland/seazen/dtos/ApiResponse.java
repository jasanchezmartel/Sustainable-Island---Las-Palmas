package com.sustainableisland.seazen.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;
    private String path;
    private Integer statusCode;

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data, String message, String path) {
        ApiResponse<T> response = new ApiResponse<>(
                true,
                message,
                data,
                LocalDateTime.now(),
                path,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data, String path) {
        ApiResponse<T> response = new ApiResponse<>(
                true,
                "Recurso creado exitosamente",
                data,
                LocalDateTime.now(),
                path,
                HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(String message, String path, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(
                false,
                message,
                null,
                LocalDateTime.now(),
                path,
                status.value());
        return ResponseEntity.status(status).body(response);
    }
}
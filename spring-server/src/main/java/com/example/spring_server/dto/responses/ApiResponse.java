package com.example.spring_server.dto.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {

    private Boolean status;
    private String message;
    private T data;

    public ApiResponse(Boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters
    public Boolean getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    // Convenience method to return ResponseEntity with this ApiResponse
    public ResponseEntity<ApiResponse<T>> toResponseEntity(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }
}

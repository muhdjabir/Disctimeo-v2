package com.example.spring_server.dto.requests;

public class UserRequestDTO {
    private String message;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

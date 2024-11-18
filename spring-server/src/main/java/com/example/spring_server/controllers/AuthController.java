package com.example.spring_server.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_server.dto.responses.ApiResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/register")
    public ApiResponse<String> register() {
        return new ApiResponse<>(true, "Registration successful");
    }

    @PostMapping("/login")
    public ApiResponse<String> login() {
        return new ApiResponse<>(true, "User logged in");
    }
}

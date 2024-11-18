package com.example.spring_server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_server.entities.User;
import com.example.spring_server.exceptions.user.UserNotFoundException;
import com.example.spring_server.dto.responses.ApiResponse;
import com.example.spring_server.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController extends V1BaseController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse<List<User>> getAllUsers() {
        return new ApiResponse<List<User>>(true,
                "Users retrieved",
                userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user
                .map(foundUser -> new ApiResponse<>(true, "User found", foundUser))
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));
    }

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ApiResponse<>(true,
                "User created",
                newUser);
    }
}

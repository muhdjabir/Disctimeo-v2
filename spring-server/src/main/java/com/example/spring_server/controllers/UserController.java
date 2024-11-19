package com.example.spring_server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.spring_server.entities.User;
import com.example.spring_server.dto.requests.UserDTO;
import com.example.spring_server.dto.responses.ApiResponse;
import com.example.spring_server.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return new ApiResponse<List<User>>(true,
                "Users retrieved",
                userService.getAllUsers())
                .toResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ApiResponse<>(true, "User retrieved", user)
                .toResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody UserDTO user) {
        User newUser = userService.createUser(user);
        return new ApiResponse<User>(true,
                "User created",
                newUser).toResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ApiResponse<Void>(true, "User deleted")
                .toResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        return new ApiResponse<User>(true, "User record patched", updatedUser)
                .toResponseEntity(HttpStatus.OK);
    }
}

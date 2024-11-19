package com.example.spring_server.exceptions.user;

public class UserAlreadyInEventException extends RuntimeException {
    public UserAlreadyInEventException(String message) {
        super(message);
    }
}

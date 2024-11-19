package com.example.spring_server.exceptions.user;

public class UserNotInEventException extends RuntimeException {
    public UserNotInEventException(String message) {
        super(message);
    }
}

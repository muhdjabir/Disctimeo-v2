package com.example.spring_server.exceptions.user;

public class UserAlreadyInTeamException extends RuntimeException {
    public UserAlreadyInTeamException(String message) {
        super(message);
    }
}

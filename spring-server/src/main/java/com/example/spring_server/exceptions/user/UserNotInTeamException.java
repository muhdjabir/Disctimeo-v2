package com.example.spring_server.exceptions.user;

public class UserNotInTeamException extends RuntimeException {
    public UserNotInTeamException(String message) {
        super(message);
    }

}

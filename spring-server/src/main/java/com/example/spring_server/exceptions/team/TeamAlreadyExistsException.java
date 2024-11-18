package com.example.spring_server.exceptions.team;

public class TeamAlreadyExistsException extends RuntimeException {
    public TeamAlreadyExistsException(String message) {
        super(message);
    }
}

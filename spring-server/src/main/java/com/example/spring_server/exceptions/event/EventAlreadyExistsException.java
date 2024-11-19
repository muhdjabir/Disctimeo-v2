package com.example.spring_server.exceptions.event;

public class EventAlreadyExistsException extends RuntimeException {
    public EventAlreadyExistsException(String message) {
        super(message);
    }
}

package com.example.spring_server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.spring_server.dto.responses.ApiResponse;
import com.example.spring_server.exceptions.event.EventAlreadyExistsException;
import com.example.spring_server.exceptions.event.EventNotFoundException;
import com.example.spring_server.exceptions.team.TeamAlreadyExistsException;
import com.example.spring_server.exceptions.team.TeamNotFoundException;
import com.example.spring_server.exceptions.user.UserAlreadyExistsException;
import com.example.spring_server.exceptions.user.UserAlreadyInTeamException;
import com.example.spring_server.exceptions.user.UserNotFoundException;
import com.example.spring_server.exceptions.user.UserNotInTeamException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException exception) {
        ApiResponse<String> response = new ApiResponse<>(false, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        ApiResponse<String> response = new ApiResponse<>(false, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyInTeamException.class)
    public ResponseEntity<ApiResponse<String>> handleUserAlreadyInTeamException(UserAlreadyInTeamException exception) {
        ApiResponse<String> response = new ApiResponse<>(false, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotInTeamException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotInTeamException(UserNotInTeamException exception) {
        ApiResponse<String> response = new ApiResponse<>(false, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleTeamNotFoundException(TeamNotFoundException exception) {
        ApiResponse<String> response = new ApiResponse<>(false, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeamAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleTeamAlreadyExistsException(TeamAlreadyExistsException exception) {
        ApiResponse<String> response = new ApiResponse<>(false, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEventNotFoundException(EventNotFoundException exception) {
        ApiResponse<String> response = new ApiResponse<>(false, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleEventAlreadyExistsException(
            EventAlreadyExistsException exception) {
        ApiResponse<String> response = new ApiResponse<>(false, exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
        ApiResponse<String> response = new ApiResponse<String>(false, "an unexpected error occured", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

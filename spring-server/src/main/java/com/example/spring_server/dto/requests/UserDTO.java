package com.example.spring_server.dto.requests;

import com.example.spring_server.enums.Role;
import com.example.spring_server.enums.Position;
import java.util.Date;

public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private Role role; // Use Role Enum instead of String
    private Position position; // Use Position Enum instead of String
    private Date startedPlaying;

    // Default constructor
    public UserDTO() {
    }

    // Constructor for easy initialization
    public UserDTO(String username, String email, Role role, Position position, Date startedPlaying) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.position = position;
        this.startedPlaying = startedPlaying;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getStartedPlaying() {
        return startedPlaying;
    }

    public void setStartedPlaying(Date startedPlaying) {
        this.startedPlaying = startedPlaying;
    }
}

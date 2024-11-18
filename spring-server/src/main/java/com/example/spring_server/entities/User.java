package com.example.spring_server.entities;

import java.util.Date;
import java.util.Objects;

import com.example.spring_server.dto.requests.UserDTO;
import com.example.spring_server.enums.Position;
import com.example.spring_server.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING) // Ensures that the Role is stored as a string in the database
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING) // Ensures that the Position is stored as a string in the database
    @Column(nullable = false)
    private Position position;

    @Temporal(TemporalType.DATE)
    private Date startedPlaying;

    public User() {
    }

    // Constructor for all fields
    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
        this.role = userDTO.getRole();
        this.position = userDTO.getPosition();
        this.startedPlaying = userDTO.getStartedPlaying();
    }

    // Getters and setters
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

    // Override equals() and hashCode() for entity comparisons
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Override toString() for debugging and logging
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", position=" + position +
                ", startedPlaying=" + startedPlaying +
                '}';
    }
}

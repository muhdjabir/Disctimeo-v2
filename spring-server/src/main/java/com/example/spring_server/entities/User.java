package com.example.spring_server.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.example.spring_server.dto.requests.UserDTO;
import com.example.spring_server.enums.Position;
import com.example.spring_server.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @Temporal(TemporalType.DATE)
    private Date startedPlaying;

    @ManyToMany
    @JoinTable(name = "user_team", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
    @JsonBackReference
    private Set<Team> teams = new HashSet<>();

    // Many-to-many relationship with Event (registration of users for events)
    @ManyToMany
    @JoinTable(name = "event_registration", // Name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // Column for the user
            inverseJoinColumns = @JoinColumn(name = "event_id") // Column for the event
    )
    @JsonManagedReference
    private Set<Event> events = new HashSet<>(); // Events that the user is registered for

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

    public User(Long id, String username, String email, Role role, Position position, Date startedPlaying) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.position = position;
        this.startedPlaying = startedPlaying;
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

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
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
                ", teams =" + teams +
                ", events=" + events +
                '}';
    }
}

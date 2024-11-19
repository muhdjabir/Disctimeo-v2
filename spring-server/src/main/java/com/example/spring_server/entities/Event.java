package com.example.spring_server.entities;

import com.example.spring_server.enums.EventType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType type; // Define an enum EventType for types like Tournament, Pickup, etc.

    @Column(length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "event_date", nullable = false)
    private Date eventDate;

    @Column(name = "registration_details")
    private String registrationDetails;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(mappedBy = "events")
    @JsonBackReference
    private Set<User> users = new HashSet<>();

    public Event(String name, EventType type, String description, Date eventDate, String registrationDetails, Team team,
            User user) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.eventDate = eventDate;
        this.registrationDetails = registrationDetails;
        this.team = team; // Team may be null
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getRegistrationDetails() {
        return registrationDetails;
    }

    public void setRegistrationDetails(String registrationDetails) {
        this.registrationDetails = registrationDetails;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

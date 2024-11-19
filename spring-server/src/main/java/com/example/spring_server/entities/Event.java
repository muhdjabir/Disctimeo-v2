package com.example.spring_server.entities;

import com.example.spring_server.enums.EventType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    // Nullable ManyToOne for the club that created the event
    @ManyToOne
    @JoinColumn(name = "creator_team_id", nullable = true)
    private Team creatorTeam;

    // Nullable ManyToOne for the user that created the event
    @ManyToOne
    @JoinColumn(name = "creator_user_id", nullable = true)
    private User creatorUser;

    // ManyToMany for participants in the event
    @ManyToMany
    @JoinTable(name = "event_participants", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonManagedReference
    private Set<User> participants = new HashSet<>();

    public Event() {
    }

    public Event(String name, EventType type, String description, Date eventDate, String registrationDetails,
            Team creatorTeam, User creatorUser) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.eventDate = eventDate;
        this.registrationDetails = registrationDetails;
        this.creatorTeam = creatorTeam; // Nullable
        this.creatorUser = creatorUser; // Nullable
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

    public Team getCreatorTeam() {
        return creatorTeam;
    }

    public void setCreatorTeam(Team creatorTeam) {
        this.creatorTeam = creatorTeam;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}

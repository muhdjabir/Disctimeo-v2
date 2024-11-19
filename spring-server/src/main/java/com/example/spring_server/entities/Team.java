package com.example.spring_server.entities;

import java.util.HashSet;
import java.util.Set;

import com.example.spring_server.enums.TeamType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private TeamType type;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String description;

    @Column(name = "year_established")
    private int yearEstablished;

    @Column
    private String venue;

    @OneToMany(mappedBy = "team")
    private Set<Event> events;

    @ManyToMany(mappedBy = "teams", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<User> users = new HashSet<>();

    public Team() {
    }

    public Team(String name, TeamType type, String email, String description, int yearEstablished, String venue) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.description = description;
        this.yearEstablished = yearEstablished;
        this.venue = venue;
    }

    // Getters and setters
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

    public TeamType getType() {
        return type;
    }

    public void setType(TeamType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(int yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
        Team team = (Team) o;
        return id != null && id.equals(team.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // Override toString() for debugging and logging
    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", yearEstablished=" + yearEstablished +
                ", users=" + users +
                ", events=" + events +
                '}';
    }
}

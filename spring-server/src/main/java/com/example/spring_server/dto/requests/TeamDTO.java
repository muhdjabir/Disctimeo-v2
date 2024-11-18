package com.example.spring_server.dto.requests;

import com.example.spring_server.enums.TeamType;

public class TeamDTO {

    private String name;
    private TeamType type;
    private String email;
    private String description;
    private int yearEstablished;
    private String venue;

    // Default constructor
    public TeamDTO() {
    }

    // Constructor for easy initialization
    public TeamDTO(String name, TeamType type, String email, String description, int yearEstablished, String venue) {
        this.name = name;
        this.type = type;
        this.email = email;
        this.description = description;
        this.yearEstablished = yearEstablished;
        this.venue = venue;
    }

    // Getters and setters for all fields
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
}

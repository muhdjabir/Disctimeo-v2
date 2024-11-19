package com.example.spring_server.dto.requests;

import com.example.spring_server.enums.EventType;
import java.util.Date;

public class EventDTO {

    private String name;
    private EventType type;
    private String description;
    private Date eventDate;
    private String registrationDetails;
    private Long teamId; // Nullable, as not all events are linked to a team
    private Long userId;

    // Constructors
    public EventDTO() {
    }

    public EventDTO(String name, EventType type, String description, Date eventDate, String registrationDetails,
            Long teamId, Long userId) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.eventDate = eventDate;
        this.registrationDetails = registrationDetails;
        this.teamId = teamId;
        this.userId = userId;
    }

    // Getters and setters
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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

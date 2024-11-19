package com.example.spring_server.dto.requests;

import com.example.spring_server.enums.EventType;
import java.util.Date;
import java.util.Set;

public class EventDTO {

    private String name;
    private EventType type;
    private String description;
    private Date eventDate;
    private String registrationDetails;
    private Long creatorClubId; // Nullable, the club creating the event
    private Long creatorUserId; // Nullable, the user creating the event
    private Set<Long> participantIds; // IDs of participants (nullable if no participants initially)

    // Constructors
    public EventDTO() {
    }

    public EventDTO(String name, EventType type, String description, Date eventDate, String registrationDetails,
            Long creatorClubId, Long creatorUserId, Set<Long> participantIds) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.eventDate = eventDate;
        this.registrationDetails = registrationDetails;
        this.creatorClubId = creatorClubId;
        this.creatorUserId = creatorUserId;
        this.participantIds = participantIds;
    }

    // Getters and Setters
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

    public Long getCreatorClubId() {
        return creatorClubId;
    }

    public void setCreatorClubId(Long creatorClubId) {
        this.creatorClubId = creatorClubId;
    }

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Set<Long> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(Set<Long> participantIds) {
        this.participantIds = participantIds;
    }
}
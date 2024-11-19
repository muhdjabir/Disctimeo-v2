package com.example.spring_server.services;

import com.example.spring_server.entities.Event;
import com.example.spring_server.entities.Team;
import com.example.spring_server.entities.User;
import com.example.spring_server.repositories.EventRepository;
import com.example.spring_server.dto.requests.EventDTO;
import com.example.spring_server.exceptions.event.EventNotFoundException;
import com.example.spring_server.exceptions.event.EventAlreadyExistsException;
import com.example.spring_server.exceptions.user.UserAlreadyInEventException;
import com.example.spring_server.exceptions.user.UserNotInEventException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;
    private final TeamService teamService;

    @Autowired
    public EventService(EventRepository eventRepository, UserService userService, TeamService teamService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.teamService = teamService;
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get an event by its ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event with id " + id + " does not exist"));
    }

    // Create a new event
    public Event createEvent(EventDTO eventDTO) {
        // Check if the event already exists (optional check based on name)
        boolean eventExists = eventRepository.existsByName(eventDTO.getName());
        if (eventExists) {
            throw new EventAlreadyExistsException("Event with name " + eventDTO.getName() + " already exists");
        }

        // Fetch the club (if provided) using TeamService
        Team creatorClub = null;
        if (eventDTO.getCreatorClubId() != null) {
            creatorClub = teamService.getTeamById(eventDTO.getCreatorClubId());
        }

        // Fetch the creator user (if provided) using UserService
        User creatorUser = null;
        if (eventDTO.getCreatorUserId() != null) {
            creatorUser = userService.getUserById(eventDTO.getCreatorUserId());
        }

        // Create a new Event and save it
        Event newEvent = new Event(eventDTO.getName(), eventDTO.getType(), eventDTO.getDescription(),
                eventDTO.getEventDate(), eventDTO.getRegistrationDetails(), creatorClub, creatorUser);

        return eventRepository.save(newEvent);
    }

    // Delete an event by its ID
    public void deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new EventNotFoundException("Event not found with id: " + id);
        }
    }

    // Update an event's details
    public Event updateEvent(Long id, EventDTO eventDTO) {
        Event existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));

        // Update fields if present in the DTO (null values are ignored)
        if (eventDTO.getName() != null) {
            existingEvent.setName(eventDTO.getName());
        }
        if (eventDTO.getType() != null) {
            existingEvent.setType(eventDTO.getType());
        }
        if (eventDTO.getDescription() != null) {
            existingEvent.setDescription(eventDTO.getDescription());
        }
        if (eventDTO.getEventDate() != null) {
            existingEvent.setEventDate(eventDTO.getEventDate());
        }
        if (eventDTO.getRegistrationDetails() != null) {
            existingEvent.setRegistrationDetails(eventDTO.getRegistrationDetails());
        }

        return eventRepository.save(existingEvent);
    }

    // Add a participant to an event
    public Event addParticipant(Long eventId, Long userId) {
        Event event = getEventById(eventId);
        User user = userService.getUserById(userId);

        if (event.getParticipants().contains(user)) {
            throw new UserAlreadyInEventException("User is already a participant in this event");
        }

        event.getParticipants().add(user);
        return eventRepository.save(event);
    }

    // Remove a participant from an event
    public Event removeParticipant(Long eventId, Long userId) {
        Event event = getEventById(eventId);
        User user = userService.getUserById(userId);

        if (!event.getParticipants().contains(user)) {
            throw new UserNotInEventException("User is not a participant in this event");
        }

        event.getParticipants().remove(user);
        return eventRepository.save(event);
    }
}

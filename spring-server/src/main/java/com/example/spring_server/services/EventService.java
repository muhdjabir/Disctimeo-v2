package com.example.spring_server.services;

import com.example.spring_server.entities.Event;
import com.example.spring_server.entities.Team;
import com.example.spring_server.entities.User;
import com.example.spring_server.repositories.EventRepository;
import com.example.spring_server.dto.requests.EventDTO;
import com.example.spring_server.exceptions.event.EventAlreadyExistsException;
import com.example.spring_server.exceptions.event.EventNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService; // Inject UserService
    private final TeamService teamService;

    @Autowired
    public EventService(EventRepository eventRepository, UserService userService, TeamService teamService) {
        this.eventRepository = eventRepository;
        this.userService = userService; // Initialize UserService
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

    public Event createEvent(EventDTO eventDTO) {
        // Check if the event already exists (optional check based on name)
        boolean eventExists = eventRepository.existsByName(eventDTO.getName());
        if (eventExists) {
            throw new EventAlreadyExistsException("Event with name " + eventDTO.getName() + " already exists");
        }

        // Fetch the Team using the TeamService
        Team team = null;
        if (eventDTO.getTeamId() != null) {
            team = teamService.getTeamById(eventDTO.getTeamId()); // Fetch the Team by ID
        }

        // Fetch the User using the UserService (Instead of userRepository)
        User user = userService.getUserById(eventDTO.getUserId()); // Call UserService to fetch the user

        // Create a new Event and save it
        Event newEvent = new Event(eventDTO.getName(), eventDTO.getType(), eventDTO.getDescription(),
                eventDTO.getEventDate(), eventDTO.getRegistrationDetails(), team, user);

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

}

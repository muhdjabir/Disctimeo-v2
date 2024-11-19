package com.example.spring_server.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.spring_server.dto.requests.EventDTO;
import com.example.spring_server.dto.responses.ApiResponse;
import com.example.spring_server.entities.Event;
import com.example.spring_server.services.EventService;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Get all events
    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        ApiResponse<List<Event>> response = new ApiResponse<>(true, "Events retrieved successfully", events);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get an event by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        ApiResponse<Event> response = new ApiResponse<>(true, "Event retrieved successfully", event);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Create a new event
    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@RequestBody EventDTO eventDTO) {
        Event createdEvent = eventService.createEvent(eventDTO);
        ApiResponse<Event> response = new ApiResponse<>(true, "Event created successfully", createdEvent);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update an existing event
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Event>> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        Event updatedEvent = eventService.updateEvent(id, eventDTO);
        ApiResponse<Event> response = new ApiResponse<>(true, "Event updated successfully", updatedEvent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete an event by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Event deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    // Add a participant to an event
    @PostMapping("/{eventId}/participants/{userId}")
    public ResponseEntity<ApiResponse<Event>> addParticipant(@PathVariable Long eventId, @PathVariable Long userId) {
        Event updatedEvent = eventService.addParticipant(eventId, userId);
        ApiResponse<Event> response = new ApiResponse<>(true, "Participant added to event successfully", updatedEvent);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    // Remove a participant from an event
    @DeleteMapping("/{eventId}/participants/{userId}")
    public ResponseEntity<ApiResponse<Event>> removeParticipant(@PathVariable Long eventId, @PathVariable Long userId) {
        Event updatedEvent = eventService.removeParticipant(eventId, userId);
        ApiResponse<Event> response = new ApiResponse<>(true, "Participant removed from event successfully",
                updatedEvent);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}

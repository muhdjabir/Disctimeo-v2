package com.example.spring_server;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.spring_server.controllers.EventController;
import com.example.spring_server.dto.requests.EventDTO;
import com.example.spring_server.entities.Event;
import com.example.spring_server.enums.EventType;
import com.example.spring_server.services.EventService;

@WebMvcTest(EventController.class)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    private Event event;
    private List<Event> eventList;

    @BeforeEach
    void setUp() {
        // Set up sample event data
        event = new Event("Summer Frisbee Tournament", EventType.TOURNAMENT, "A fun summer frisbee tournament",
                new Date(), "Register at example.com", null, null);
        event.setId(1L);
        eventList = List.of(event);
    }

    @Test
    void testGetAllEvents() throws Exception {
        when(eventService.getAllEvents()).thenReturn(eventList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/events"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Events retrieved successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("Summer Frisbee Tournament"));

        verify(eventService, times(1)).getAllEvents();
    }

    @Test
    void testGetEventById() throws Exception {
        when(eventService.getEventById(1L)).thenReturn(event);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/events/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Event retrieved successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Summer Frisbee Tournament"));

        verify(eventService, times(1)).getEventById(1L);
    }

    @Test
    void testCreateEvent() throws Exception {
        when(eventService.createEvent(any(EventDTO.class))).thenReturn(event);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/events")
                .contentType("application/json")
                .content("""
                        {
                            "name": "Summer Frisbee Tournament",
                            "type": "TOURNAMENT",
                            "description": "A fun summer frisbee tournament",
                            "eventDate": "2024-06-15T10:00:00",
                            "registrationDetails": "Register at example.com",
                            "creatorClubId": null,
                            "creatorUserId": null
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Event created successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Summer Frisbee Tournament"));

        verify(eventService, times(1)).createEvent(any(EventDTO.class));
    }

    @Test
    void testUpdateEvent() throws Exception {
        // Set the name to the updated value in the Event object
        event.setName("Summer Frisbee Tournament Updated");

        // Prepare EventDTO with expected changes
        EventDTO eventDTO = new EventDTO();
        eventDTO.setName("Summer Frisbee Tournament Updated");

        // Mocking the service layer
        // Use eq() for the eventId and any() for the EventDTO
        when(eventService.updateEvent(eq(1L), any(EventDTO.class))).thenReturn(event);

        // Perform the patch request with the updated JSON payload
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/events/1")
                .contentType("application/json")
                .content("""
                        {
                            "name": "Summer Frisbee Tournament Updated"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Event updated successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("Summer Frisbee Tournament Updated"));

        // Verifying that the service method is called with correct parameters
        verify(eventService, times(1)).updateEvent(eq(1L), any(EventDTO.class));
    }

    @Test
    void testDeleteEvent() throws Exception {
        doNothing().when(eventService).deleteEvent(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/events/1"))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Event deleted successfully"));

        verify(eventService, times(1)).deleteEvent(1L);
    }

    @Test
    void testAddParticipant() throws Exception {
        when(eventService.addParticipant(1L, 1L)).thenReturn(event);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/events/1/participants/1"))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.message").value("Participant added to event successfully"));

        verify(eventService, times(1)).addParticipant(1L, 1L);
    }

    @Test
    void testRemoveParticipant() throws Exception {
        when(eventService.removeParticipant(1L, 1L)).thenReturn(event);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/events/1/participants/1"))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Participant removed from event successfully"));

        verify(eventService, times(1)).removeParticipant(1L, 1L);
    }
}

package com.example.spring_server;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.spring_server.controllers.TeamController;
import com.example.spring_server.dto.requests.TeamDTO;
import com.example.spring_server.entities.Team;
import com.example.spring_server.enums.TeamType;
import com.example.spring_server.services.TeamService;

@WebMvcTest(TeamController.class)
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    private Team team;
    private List<Team> teamList;

    @BeforeEach
    void setUp() {
        // Set up sample team data
        team = new Team("West Coast Rascals", TeamType.CLUB, "contact@wcrascals.com", "A competitive Frisbee club",
                2012, "West Coast Park");
        team.setId(1L);
        teamList = List.of(team);
    }

    @Test
    void testGetAllTeams() throws Exception {
        when(teamService.getAllTeams()).thenReturn(teamList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/teams"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Teams retrieved successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].name").value("West Coast Rascals"));

        verify(teamService, times(1)).getAllTeams();
    }

    @Test
    void testGetTeamById() throws Exception {
        when(teamService.getTeamById(1L)).thenReturn(team);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/teams/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Team retrieved successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("West Coast Rascals"));

        verify(teamService, times(1)).getTeamById(1L);
    }

    @Test
    void testCreateTeam() throws Exception {
        when(teamService.createTeam(any(TeamDTO.class))).thenReturn(team);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/teams")
                .contentType("application/json")
                .content("""
                        {
                            "name": "West Coast Rascals",
                            "type": "CLUB",
                            "email": "contact@wcrascals.com",
                            "description": "A competitive Frisbee club",
                            "yearEstablished": 2012,
                            "venue": "West Coast Park"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Team created successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("West Coast Rascals"));

        verify(teamService, times(1)).createTeam(any(TeamDTO.class));
    }

    @Test
    void testUpdateTeam() throws Exception {
        Team updatedTeam = new Team("West Coast Rascals Updated", TeamType.CLUB, "contact@wcrascals.com",
                "Updated description", 2015, "Updated Venue");
        updatedTeam.setId(1L);

        when(teamService.updateTeam(eq(1L), any(TeamDTO.class))).thenReturn(updatedTeam);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/teams/1")
                .contentType("application/json")
                .content("""
                        {
                            "name": "West Coast Rascals Updated",
                            "type": "CLUB",
                            "email": "contact@wcrascals.com",
                            "description": "Updated description",
                            "yearEstablished": 2015,
                            "venue": "Updated Venue"
                        }
                        """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Team updated successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("West Coast Rascals Updated"));

        verify(teamService, times(1)).updateTeam(eq(1L), any(TeamDTO.class));
    }

    @Test
    void testDeleteTeam() throws Exception {
        // No return value expected for delete
        doNothing().when(teamService).deleteTeam(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/teams/1"))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Team deleted successfully"));

        verify(teamService, times(1)).deleteTeam(1L);
    }

    @Test
    void testAddUserToTeam() throws Exception {
        Team team = new Team("West Coast Rascals", TeamType.CLUB, "contact@wcrascals.com",
                "A competitive Frisbee club", 2012, "West Coast Park");
        team.setId(1L);

        when(teamService.addUserToTeam(eq(1L), eq(1L))).thenReturn(team);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/teams/1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Player added to team successfully."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("West Coast Rascals"));

        verify(teamService, times(1)).addUserToTeam(1L, 1L);
    }

    @Test
    void testRemoveUserFromTeam() throws Exception {
        Team team = new Team("West Coast Rascals", TeamType.CLUB, "contact@wcrascals.com",
                "A competitive Frisbee club", 2012, "West Coast Park");
        team.setId(1L);

        when(teamService.removeUserFromTeam(eq(1L), eq(1L))).thenReturn(team);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/teams/1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Player removed from team successfully."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("West Coast Rascals"));

        verify(teamService, times(1)).removeUserFromTeam(1L, 1L);
    }
}

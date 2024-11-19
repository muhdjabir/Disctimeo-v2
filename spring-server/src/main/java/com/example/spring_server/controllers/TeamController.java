package com.example.spring_server.controllers;

import com.example.spring_server.dto.requests.TeamDTO;
import com.example.spring_server.dto.responses.ApiResponse;
import com.example.spring_server.entities.Team;
import com.example.spring_server.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // Get all teams
    @GetMapping
    public ResponseEntity<ApiResponse<List<Team>>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        ApiResponse<List<Team>> response = new ApiResponse<>(true, "Teams retrieved successfully", teams);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get a team by its ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Team>> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        ApiResponse<Team> response = new ApiResponse<>(true, "Team retrieved successfully", team);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Create a new team
    @PostMapping
    public ResponseEntity<ApiResponse<Team>> createTeam(@RequestBody TeamDTO teamDTO) {
        Team createdTeam = teamService.createTeam(teamDTO);
        ApiResponse<Team> response = new ApiResponse<>(true, "Team created successfully", createdTeam);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update an existing team
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Team>> updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        Team updatedTeam = teamService.updateTeam(id, teamDTO);
        ApiResponse<Team> response = new ApiResponse<>(true, "Team updated successfully", updatedTeam);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete a team by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Team deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    // Endpoint to add a user to a team
    @PostMapping("/{teamId}/users/{userId}")
    public ResponseEntity<ApiResponse<Team>> addUserToTeam(@PathVariable Long userId, @PathVariable Long teamId) {
        Team team = teamService.addUserToTeam(userId, teamId);
        ApiResponse<Team> response = new ApiResponse<>(true, "Player added to team successfully.", team);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    // Endpoint to remove a user from a team
    @DeleteMapping("/{teamId}/users/{userId}")
    public ResponseEntity<ApiResponse<Team>> removeUserFromTeam(@PathVariable Long userId, @PathVariable Long teamId) {
        Team team = teamService.removeUserFromTeam(userId, teamId);
        ApiResponse<Team> response = new ApiResponse<>(true, "Player removed from team successfully.", team);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}

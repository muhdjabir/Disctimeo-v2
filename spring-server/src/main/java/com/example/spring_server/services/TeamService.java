package com.example.spring_server.services;

import com.example.spring_server.dto.requests.TeamDTO;
import com.example.spring_server.entities.Team;
import com.example.spring_server.exceptions.team.TeamAlreadyExistsException;
import com.example.spring_server.exceptions.team.TeamNotFoundException;
import com.example.spring_server.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Get all teams
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Get a team by its ID
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team with id " + id + " does not exist"));
    }

    // Get a team by its name
    public Team getTeamByName(String name) {
        Team team = teamRepository.findByName(name);
        if (team == null) {
            throw new TeamNotFoundException("Team with name " + name + " does not exist");
        }
        return team;
    }

    // Create a new team
    public Team createTeam(TeamDTO teamDTO) {
        // Check if the team already exists (optional check, based on uniqueness of name
        // or email)
        boolean teamExists = teamRepository.existsByName(teamDTO.getName());
        if (teamExists) {
            throw new TeamAlreadyExistsException("Team with name " + teamDTO.getName() + " already exists");
        }
        Team newTeam = new Team(teamDTO.getName(), teamDTO.getType(), teamDTO.getEmail(),
                teamDTO.getDescription(), teamDTO.getYearEstablished(), teamDTO.getVenue());
        return teamRepository.save(newTeam);
    }

    // Delete a team by its ID
    public void deleteTeam(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
        } else {
            throw new TeamNotFoundException("Team not found with id: " + id);
        }
    }

    // Update a team's details
    public Team updateTeam(Long id, TeamDTO teamDTO) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + id));

        // Update fields if present in the DTO (null values are ignored)
        if (teamDTO.getName() != null) {
            existingTeam.setName(teamDTO.getName());
        }
        if (teamDTO.getType() != null) {
            existingTeam.setType(teamDTO.getType());
        }
        if (teamDTO.getEmail() != null) {
            existingTeam.setEmail(teamDTO.getEmail());
        }
        if (teamDTO.getDescription() != null) {
            existingTeam.setDescription(teamDTO.getDescription());
        }
        if (teamDTO.getYearEstablished() != 0) {
            existingTeam.setYearEstablished(teamDTO.getYearEstablished());
        }
        if (teamDTO.getVenue() != null) {
            existingTeam.setVenue(teamDTO.getVenue());
        }

        return teamRepository.save(existingTeam);
    }
}
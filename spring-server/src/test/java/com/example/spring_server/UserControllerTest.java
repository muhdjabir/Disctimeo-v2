package com.example.spring_server;

import com.example.spring_server.controllers.UserController;
import com.example.spring_server.dto.requests.UserDTO;
import com.example.spring_server.entities.Team;
import com.example.spring_server.entities.User;
import com.example.spring_server.enums.Position;
import com.example.spring_server.enums.Role;
import com.example.spring_server.enums.TeamType;
import com.example.spring_server.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        // Set up some sample user data for testing
        user = new User(1L, "john_doe", "john.doe@example.com", Role.ADMIN, Position.HANDLER, new Date());
        userList = Arrays.asList(user);
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Users retrieved"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].username").value("john_doe"));
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User retrieved"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("john_doe"));
    }

    @Test
    void testCreateUser() throws Exception {
        UserDTO userDTO = new UserDTO("jane_doe", "jane.doe@example.com", Role.MEMBER, Position.CUTTER, new Date());
        User newUser = new User(userDTO);
        // Mock the service to return the predefined `user` object when called
        when(userService.createUser(any(UserDTO.class))).thenReturn(newUser);

        // JSON representation of the UserDTO for the test
        String userJson = """
                    {
                        "username": "jane_doe",
                        "email": "jane.doe@example.com",
                        "role": "MEMBER",
                        "position": "CUTTER",
                        "startedPlaying": "2021-06-15"
                    }
                """;

        // Perform the POST request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                .contentType("application/json")
                .content(userJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User created"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("jane_doe"));
    }

    @Test
    void testDeleteUser() throws Exception {
        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User deleted"));

        // Verify the service method was called with the correct parameter
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    void testUpdateUser() throws Exception {
        // Create a UserDTO to represent the updated data
        UserDTO updatedUserDTO = new UserDTO("john_doe_updated", "john.doe.updated@example.com", Role.ADMIN,
                Position.HANDLER, new Date());

        // Mock the updated user response
        User updatedUser = new User(updatedUserDTO);
        updatedUser.setId(1L);

        // Mock the service behavior
        when(userService.updateUser(eq(1L), any(UserDTO.class))).thenReturn(updatedUser);

        // Perform the PATCH request with JSON payload
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/users/1")
                .contentType("application/json")
                .content(
                        "{\"username\":\"john_doe_updated\", \"email\":\"john.doe.updated@example.com\", \"role\":\"ADMIN\", \"position\":\"HANDLER\", \"startedPlaying\":\"2021-06-15\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User record patched"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("john_doe_updated"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("john.doe.updated@example.com"));

        // Verify the service method was called with the correct parameters
        verify(userService, times(1)).updateUser(eq(1L), any(UserDTO.class));
    }

    @Test
    void testAddUserToTeam() throws Exception {
        // Mock a team and user
        Team team = new Team("West Coast Rascals", TeamType.CLUB, "contact@wcrascals.com",
                "A competitive Frisbee club focused on developing skills and competing in tournaments. Located at West Coast",
                2012, "West Coast Park");
        team.setId(1L);

        user.getTeams().add(team);

        // Mock the service behavior
        when(userService.addUserToTeam(1L, 1L)).thenReturn(user);

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/1/teams/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User added to team"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("john_doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.teams[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.teams[0].name").value("West Coast Rascals"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.teams[0].email").value("contact@wcrascals.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.teams[0].description").value(
                        "A competitive Frisbee club focused on developing skills and competing in tournaments. Located at West Coast"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.teams[0].venue").value("West Coast Park"));

        // Verify the service method was called with correct parameters
        verify(userService, times(1)).addUserToTeam(1L, 1L);
    }

    @Test
    void testRemoveUserFromTeam() throws Exception {
        // Mock a user without any teams after removal
        user.getTeams().clear();

        // Mock the service behavior
        when(userService.removeUserFromTeam(1L, 1L)).thenReturn(user);

        // Perform the DELETE request
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/1/teams/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User removed from team"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").value("john_doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.teams").isEmpty());

        // Verify the service method was called with correct parameters
        verify(userService, times(1)).removeUserFromTeam(1L, 1L);
    }

}

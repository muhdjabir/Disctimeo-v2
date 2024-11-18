package com.example.spring_server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.spring_server.controllers.AuthController;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegister() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Registration successful"));
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User logged in"));
    }
}

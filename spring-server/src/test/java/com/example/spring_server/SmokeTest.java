package com.example.spring_server;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.spring_server.controllers.AuthController;
import com.example.spring_server.controllers.TeamController;
import com.example.spring_server.controllers.UserController;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private UserController userController;

    @Autowired
    private TeamController teamController;

    @Autowired
    private AuthController authController;

    @Test
    void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
        assertThat(teamController).isNotNull();
        assertThat(authController).isNotNull();
    }
}

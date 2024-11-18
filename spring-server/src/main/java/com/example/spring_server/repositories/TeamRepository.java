package com.example.spring_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_server.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    boolean existsByName(String name);

    Team findByName(String name);
}

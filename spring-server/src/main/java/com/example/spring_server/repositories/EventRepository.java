package com.example.spring_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_server.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByName(String name);

    Event findByName(String name);
}

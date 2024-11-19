package com.example.spring_server.enums;

public enum EventType {
    TOURNAMENT("Tournament"),
    PICKUP("Pickup"),
    TRIAL("Trial");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

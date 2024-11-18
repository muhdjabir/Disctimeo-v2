package com.example.spring_server.enums;

public enum TeamType {
    CLUB("Club"),
    SCHOOL("School"),
    RECREATIONAL("Recreational");

    private final String displayName;

    TeamType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
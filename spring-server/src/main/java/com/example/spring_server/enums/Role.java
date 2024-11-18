package com.example.spring_server.enums;

public enum Role {
    MEMBER("Member"),
    ADMIN("Admin"),
    CLUB_ADMIN("Club Admin");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
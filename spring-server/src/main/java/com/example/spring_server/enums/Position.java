package com.example.spring_server.enums;

public enum Position {
    CUTTER("Cutter"),
    HANDLER("Handler"),
    HYBRID("Hybrid");

    private final String displayName;

    Position(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
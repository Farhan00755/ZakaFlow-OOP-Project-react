package com.zakaflow.zakaflow.model;

public enum ProgramStatus {
    DRAFT("Draft"),
    ACTIVE("Published / Active"),
    PAUSED("Paused"),
    CLOSED("Completed / Closed");

    private final String label;

    ProgramStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

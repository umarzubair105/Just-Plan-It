package com.uz.justplan.lookup;

public enum ConcernStatus {
    OPEN("OPEN"),
    ANSWERED("ANSWERED"),
    RESOLVED("RESOLVED"),
    CLOSED("CLOSED");

    private final String value;

    ConcernStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

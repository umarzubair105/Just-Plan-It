package com.uz.justplan.lookup;

public enum AssignmentStatus {

    OPEN("OPEN"), STARTED("STARTED"), ON_HOLD("ON_HOLD"), COMPLETED("COMPLETED"),
    OVERDUE("OVERDUE");

    private final String value;

    AssignmentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

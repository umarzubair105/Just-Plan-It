package com.uz.justplan.lookup;

public enum PriorityEnum {
    LOW("LOW"), MEDIUM("MEDIUM"), HIGH("HIGH"), HIGHEST("HIGHEST"), CRITICAL("CRITICAL");

    private final String value;

    PriorityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

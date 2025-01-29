package com.uz.justplan.lookup;

public enum ReleaseStatusEnum {
    INITIATED("INITIATED"), PLANNED("PLANNED"), STARTED("STARTED"), COMPLETED("COMPLETED"), OVERDUE("OVERDUE");

    private final String value;

    ReleaseStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

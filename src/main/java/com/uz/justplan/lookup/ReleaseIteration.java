package com.uz.justplan.lookup;

public enum ReleaseIteration {
    // Enum for release iterations
    ANNUAL("ANNUAL"),
    SEMI_ANNUAL("SEMI_ANNUAL"),
    QUARTERLY("QUARTERLY"),
    BI_MONTHLY("BI-MONTHLY"),
    MONTHLY("MONTHLY"),
    TRI_WEEKLY("TRI-WEEKLY"),
    BI_WEEKLY("BI-WEEKLY"),
    WEEKLY("WEEKLY");
    //DAILY("DAILY");


    private final String value;

    ReleaseIteration(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

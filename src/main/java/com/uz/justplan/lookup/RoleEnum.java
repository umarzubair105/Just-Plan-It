package com.uz.justplan.lookup;

public enum RoleEnum {
    ADMIN("ADMIN"),
    HR("HR"),
    PM("PM"),
    PO("PO"),
    SA("SA"),
    DBA("DBA"),
    SE("SE"),
    QAE("QAE"),
    UID("UID"),
    BA("BA");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

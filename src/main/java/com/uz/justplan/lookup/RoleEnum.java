package com.uz.justplan.lookup;

public enum RoleEnum {
    ADMIN("ADMIN"),
    HR("HR"),
    PM("PM"),
    PO("PO"),
    SA("SA"),
    DBA("DBA"),
    BSE("BSE"),
    FSE("FSE"),
    QAE("QAE"),
    UID("UID"),
    BA("BA"),
    SM("SM");
    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

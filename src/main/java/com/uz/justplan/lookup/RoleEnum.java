package com.uz.justplan.lookup;

public enum RoleEnum {
    ADMIN("ADMIN"),
    HR("HR"),
    PM("PM"),
    TR("TR");
    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

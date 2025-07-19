package com.uz.justplan.lookup;

public enum EntityDetailType {
    ATTACHED_FILE("ATTACHED_FILE"),
    URL("URL");

    private final String value;

    EntityDetailType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

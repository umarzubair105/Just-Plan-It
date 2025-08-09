package com.uz.justplan.lookup;

public enum EntityType {
    COMPANY("COMPANY"),
    RELEASE("RELEASE"),
    RESOURCE("RESOURCE"),
    PRODUCT("PRODUCT");

    private final String value;

    EntityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

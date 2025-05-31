package com.uz.justplan.lookup;

public enum EpicDetailType {

    COMMENT("COMMENT"),
    ATTACHED_FILE("ATTACHED_FILE"),
    REFERENCE("REFERENCE");

    private final String value;

    EpicDetailType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

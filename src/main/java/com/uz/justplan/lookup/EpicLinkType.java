package com.uz.justplan.lookup;

public enum EpicLinkType {

    RELATED_TO("RELATED_TO"),
    DEPEND_ON("DEPEND_ON");

    private final String value;

    EpicLinkType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

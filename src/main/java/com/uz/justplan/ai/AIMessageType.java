package com.uz.justplan.ai;

public enum AIMessageType {
    ALARM("ALARM"),
    INFO("INFO"),
    STOPPER("STOPPER");


    private final String value;

    AIMessageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

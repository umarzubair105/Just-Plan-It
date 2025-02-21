package com.uz.justplan.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.DayOfWeek;

public class DayOfWeekDeserializer extends JsonDeserializer<DayOfWeek> {
    @Override
    public DayOfWeek deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getText() == null) {
            return null;
        }
        String value = p.getText().trim();
        if (value.isEmpty()) {
            return null; // or return a default value, e.g., DayOfWeek.MONDAY
        }
        return DayOfWeek.valueOf(value.toUpperCase());
    }
}

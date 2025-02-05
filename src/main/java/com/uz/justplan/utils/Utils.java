package com.uz.justplan.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class Utils {

    public static LocalDate getLocalDateFromString(String dateStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
        }
        return null;
    }

    public static double getDoubleValue(String str) {
        if (Validation.isEmpty(str)) {
            return 0;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String getNameFromEmail(String email) {
        String namePart = email.split("@")[0].replaceAll("\\d", "")
                .replaceAll("[\\.\\-_]+", " ");
        return toTitleCase(namePart);
    }

    public static String toTitleCase(String input) {
        return Arrays.stream(input
                        .toLowerCase()
                        .split(" "))
                .map(word -> Character.toTitleCase(word.charAt(0))
                        + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}

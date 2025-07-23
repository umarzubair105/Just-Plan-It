package com.uz.justplan.utils;

import java.util.regex.Pattern;

public class Validation {
    private final static String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private final static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }
}

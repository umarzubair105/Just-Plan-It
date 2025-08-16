package com.uz.justplan.utils;

import com.uz.justplan.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SecurePasswordGenerator {
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    private static final int PASSWORD_LENGTH = 8;
    private static final String ALL = UPPERCASE + LOWERCASE + DIGITS + SYMBOLS;

    private static final SecureRandom random = new SecureRandom();
    @Autowired
    private AppProperties props;

    public static void main(String[] args) {
        //String password = generatePassword();
        //System.out.println("Generated Password: " + password);
    }

    private static char getRandomChar(final String characters) {
        return characters.charAt(random.nextInt(characters.length()));
    }


    public static String encryptPassword(final String plainPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(plainPassword);
    }

    public String generatePassword() {
        /*if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4 to include all character sets.");
        }*/

        List<Character> passwordChars = new ArrayList<>();

        // Enforce at least one character from each category
        passwordChars.add(getRandomChar(UPPERCASE));
        passwordChars.add(getRandomChar(LOWERCASE));
        passwordChars.add(getRandomChar(DIGITS));
        passwordChars.add(getRandomChar(SYMBOLS));

        // Fill the rest of the password
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            passwordChars.add(getRandomChar(ALL));
        }

        // Shuffle to avoid predictable patterns
        Collections.shuffle(passwordChars, random);

        // Convert to string
        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }
        if (props.getProduction() != null && "false".equalsIgnoreCase(props.getProduction().trim())) {
            return "12345";
        }
        return password.toString();
    }
}


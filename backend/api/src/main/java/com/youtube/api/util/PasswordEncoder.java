package com.youtube.api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(final String password) {
        return encoder.encode(password);
    }

    public static boolean matches(final String rawPassword, final String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}

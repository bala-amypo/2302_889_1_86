package com.example.demo.util;

import java.util.Set;

public class ValidationUtil {

    private static final Set<String> VALID =
            Set.of("Kharif", "Rabi", "Summer");

    public static boolean validSeason(String season) {
        return season != null && VALID.contains(season);
    }
}

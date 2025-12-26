package com.example.demo.util;

public class ValidationUtil {

    public static boolean validSeason(String season) {
        if (season == null) return false;
        return season.equals("Kharif") || season.equals("Rabi") || season.equals("Summer");
    }
}

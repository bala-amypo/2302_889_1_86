// ValidationUtil.java
package com.example.demo.util;

public class ValidationUtil {

    public static boolean validSeason(String season) {
        if (season == null) return false;
        String s = season.trim();
        return s.equalsIgnoreCase("Kharif")
                || s.equalsIgnoreCase("Rabi")
                || s.equalsIgnoreCase("Zaid");
    }
}

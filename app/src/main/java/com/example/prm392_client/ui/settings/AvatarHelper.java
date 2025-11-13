package com.example.prm392_client.ui.settings;

import android.graphics.Color;
import java.util.Random;

/**
 * Helper class for generating avatar with first letter and random background color
 */
public class AvatarHelper {
    
    // Predefined colors for avatar backgrounds
    private static final int[] AVATAR_COLORS = {
            Color.parseColor("#FF6B6B"),  // Red
            Color.parseColor("#4ECDC4"),  // Teal
            Color.parseColor("#45B7D1"),  // Blue
            Color.parseColor("#FFA07A"),  // Light Salmon
            Color.parseColor("#98D8C8"),  // Mint
            Color.parseColor("#F7DC6F"),  // Yellow
            Color.parseColor("#BB8FCE"),  // Purple
            Color.parseColor("#85C1E2"),  // Sky Blue
            Color.parseColor("#F8B739"),  // Orange
            Color.parseColor("#52BE80"),  // Green
    };

    /**
     * Get first letter from user name
     */
    public static String getFirstLetter(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            return "?";
        }
        String trimmed = userName.trim();
        return trimmed.substring(0, 1).toUpperCase();
    }

    /**
     * Get random color for avatar background based on user name
     */
    public static int getAvatarColor(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            return AVATAR_COLORS[0];
        }
        // Use hash code to ensure same user gets same color
        int index = Math.abs(userName.hashCode()) % AVATAR_COLORS.length;
        return AVATAR_COLORS[index];
    }

    /**
     * Get random color (truly random)
     */
    public static int getRandomColor() {
        Random random = new Random();
        return AVATAR_COLORS[random.nextInt(AVATAR_COLORS.length)];
    }
}








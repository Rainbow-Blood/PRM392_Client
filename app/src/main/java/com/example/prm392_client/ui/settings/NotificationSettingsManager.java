package com.example.prm392_client.ui.settings;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * Manager class for handling notification and sound settings
 */
public class NotificationSettingsManager {
    private static final String PREFS_NAME = "notification_settings";
    private static final String KEY_NOTIFICATIONS_ENABLED = "notifications_enabled";
    private static final String KEY_SOUND_ENABLED = "sound_enabled";
    private static final String CHANNEL_ID = "app_notifications_channel";

    private Context context;
    private SharedPreferences prefs;
    private NotificationManager notificationManager;

    public NotificationSettingsManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        // Create notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
    }

    /**
     * Create notification channel for Android O+
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationChannel channel = notificationManager.getNotificationChannel(CHANNEL_ID);
        if (channel == null) {
            channel = new NotificationChannel(
                    CHANNEL_ID,
                    "App Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Notifications for the app");
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Enable or disable notifications
     */
    public void setNotificationsEnabled(boolean enabled) {
        prefs.edit().putBoolean(KEY_NOTIFICATIONS_ENABLED, enabled).apply();
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if (channel != null) {
                int importance = enabled ? NotificationManager.IMPORTANCE_DEFAULT : NotificationManager.IMPORTANCE_NONE;
                channel.setImportance(importance);
                notificationManager.createNotificationChannel(channel);
            }
        } else {
            // For older Android versions, we can't directly disable notifications
            // This is handled by the system settings
        }
    }

    /**
     * Enable or disable notification sound
     */
    public void setSoundEnabled(boolean enabled) {
        prefs.edit().putBoolean(KEY_SOUND_ENABLED, enabled).apply();
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if (channel != null) {
                if (enabled) {
                    // Enable sound
                    AudioAttributes audioAttributes = new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .build();
                    channel.setSound(android.provider.Settings.System.DEFAULT_NOTIFICATION_URI, audioAttributes);
                } else {
                    // Disable sound
                    channel.setSound(null, null);
                }
                notificationManager.createNotificationChannel(channel);
            }
        } else {
            // For older versions, we can use AudioManager
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if (audioManager != null) {
                // Note: This affects system-wide sound, not just notifications
                // For app-specific control, we need to handle this in notification creation
            }
        }
    }

    /**
     * Check if notifications are enabled
     */
    public boolean areNotificationsEnabled() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if (channel != null) {
                return channel.getImportance() != NotificationManager.IMPORTANCE_NONE;
            }
        }
        return prefs.getBoolean(KEY_NOTIFICATIONS_ENABLED, true);
    }

    /**
     * Check if sound is enabled
     */
    public boolean isSoundEnabled() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(CHANNEL_ID);
            if (channel != null) {
                return channel.getSound() != null;
            }
        }
        return prefs.getBoolean(KEY_SOUND_ENABLED, true);
    }

    /**
     * Get saved notification enabled state from preferences
     */
    public boolean getSavedNotificationsEnabled() {
        return prefs.getBoolean(KEY_NOTIFICATIONS_ENABLED, true);
    }

    /**
     * Get saved sound enabled state from preferences
     */
    public boolean getSavedSoundEnabled() {
        return prefs.getBoolean(KEY_SOUND_ENABLED, true);
    }
}


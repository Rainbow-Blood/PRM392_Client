package com.example.prm392_client.ui.settings.privacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;
import com.example.prm392_client.model.setting.SettingItem;
import com.example.prm392_client.ui.settings.NotificationSettingsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Privacy Fragment
 * Displays privacy settings options
 */
public class PrivacyFragment extends Fragment {

    private RecyclerView rvPrivacySettings;
    private PrivacyAdapter privacySettingsAdapter;
    private NotificationSettingsManager notificationSettingsManager;
    private List<SettingItem> privacyItems;

    public PrivacyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_privacy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initNotificationManager();
        setupPrivacySettingsList();
        setupNavigationListener();
    }

    private NavController navController;

    /**
     * Setup navigation listener to handle bottom navigation clicks
     */
    private void setupNavigationListener() {
        View view = getView();
        if (view != null) {
            try {
                navController = Navigation.findNavController(view);
                
                // Listen for destination changes
                navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                    // If navigating to settingsFragment while we're still on this fragment, pop back
                    // Nếu điều hướng đến settingsFragment trong khi chúng ta vẫn ở fragment này, hãy quay lại
                    if (destination.getId() == R.id.settingsFragment) {
                        NavDestination currentDest = controller.getCurrentDestination();
                        if (currentDest != null && 
                            currentDest.getId() == R.id.privacyFragment) {
                            // Use post to ensure this runs after navigation
                            view.post(() -> {
                                try {
                                    controller.popBackStack(R.id.settingsFragment, false);
                                } catch (Exception e) {
                                    // Ignore
                                }
                            });
                        }
                    }
                });
            } catch (Exception e) {
                // NavController not found, ignore

            }
        }
    }

    private void initViews(View view) {
        rvPrivacySettings = view.findViewById(R.id.rv_privacy_settings);
    }

    private void initNotificationManager() {
        if (getContext() != null) {
            notificationSettingsManager = new NotificationSettingsManager(getContext());
        }
    }

    private void setupPrivacySettingsList() {
        privacyItems = new ArrayList<>();
        
        // Load saved settings
        boolean notificationsEnabled = notificationSettingsManager != null 
                ? notificationSettingsManager.getSavedNotificationsEnabled() 
                : true;
        boolean soundEnabled = notificationSettingsManager != null 
                ? notificationSettingsManager.getSavedSoundEnabled() 
                : true;
        
        privacyItems.add(new SettingItem(
                "Thông báo cả app",
                android.R.drawable.ic_dialog_info,
                "app_notifications",
                true, // Use toggle switch
                notificationsEnabled
        ));

        privacyItems.add(new SettingItem(
                "Tắt chuông",
                android.R.drawable.ic_media_play,
                "silent_mode",
                true, // Use toggle switch
                !soundEnabled // Inverted: if sound is enabled, switch is off
        ));

        privacyItems.add(new SettingItem(
                "Quyền truy cập",
                android.R.drawable.ic_menu_manage,
                "permissions",
                false,
                false
        ));

        privacySettingsAdapter = new PrivacyAdapter(privacyItems, item -> {
            handlePrivacySettingClick(item);
        });

        rvPrivacySettings.setLayoutManager(new LinearLayoutManager(getContext()));
        rvPrivacySettings.setAdapter(privacySettingsAdapter);
    }

    private void handlePrivacySettingClick(SettingItem item) {
        String type = item.getType();
        if (notificationSettingsManager == null) {
            return;
        }
        
        switch (type) {
            case "app_notifications":
                boolean notificationsEnabled = item.isSwitchChecked();
                notificationSettingsManager.setNotificationsEnabled(notificationsEnabled);
                if (notificationsEnabled) {
                    Toast.makeText(getContext(), "Thông báo đã bật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thông báo đã tắt", Toast.LENGTH_SHORT).show();
                }
                break;
            case "silent_mode":
                boolean silentMode = item.isSwitchChecked();
                // Inverted: if switch is on (silent mode), sound is off
                boolean soundEnabled = !silentMode;
                notificationSettingsManager.setSoundEnabled(soundEnabled);
                if (silentMode) {
                    Toast.makeText(getContext(), "Chuông đã tắt", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Chuông đã bật", Toast.LENGTH_SHORT).show();
                }
                break;
            case "permissions":
                Toast.makeText(getContext(), "Quyền truy cập", Toast.LENGTH_SHORT).show();
                // TODO: Open system permissions settings
                break;
        }
    }
}


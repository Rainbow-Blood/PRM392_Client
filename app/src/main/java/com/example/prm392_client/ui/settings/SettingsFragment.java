package com.example.prm392_client.ui.settings;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;
import com.example.prm392_client.model.setting.SettingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Settings Fragment - Display user profile and settings options
 */
public class SettingsFragment extends Fragment {

    private TextView tvAvatar;
    private TextView tvUserName;
    private TextView tvViewProfile;
    private RecyclerView rvSettings;
    private Button btnLogout;
    private SettingsAdapter settingsAdapter;
    
    // Sample user name - Replace with actual user data from your app
    private String userName = "Nguyễn Văn A";
    
    // Parameters for passing data to fragment
    private static final String ARG_USER_ID = "user_id";
    private static final String ARG_USER_NAME = "user_name";

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     * 
     * @param userId User ID
     * @param userName User name
     * @return A new instance of fragment SettingsFragment.
     */
    public static SettingsFragment newInstance(String userId, String userName) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        args.putString(ARG_USER_NAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get arguments passed from newInstance()
        // This is where you can initialize API calls that don't need views
        if (getArguments() != null) {
            String userId = getArguments().getString(ARG_USER_ID);
            String name = getArguments().getString(ARG_USER_NAME);
            if (name != null) {
                userName = name;
            }
            // TODO: You can call API here if needed (but views are not ready yet)
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialize views
        initViews(view);
        
        // Call API to fetch user data
        // This is the best place to call API because views are ready
        fetchUserData();
        
        // Setup user profile
        setupUserProfile();
        
        // Setup settings list
        setupSettingsList();
        
        // Setup logout button
        setupLogoutButton();
    }

    /**
     * Initialize all views

     */
    private void initViews(View view) {
        tvAvatar = view.findViewById(R.id.tv_avatar);
        tvUserName = view.findViewById(R.id.tv_user_name);
        tvViewProfile = view.findViewById(R.id.tv_view_profile);
        rvSettings = view.findViewById(R.id.rv_settings);
        btnLogout = view.findViewById(R.id.btn_logout);
    }

    /**
     * Fetch user data from API
     * This method demonstrates how to call API in Fragment
     */
    private void fetchUserData() {
        // TODO: Replace with your actual API endpoint
        
        // Example API call using Retrofit (uncomment and modify as needed)
        /*
        ApiService apiService = RetrofitClient.getApiService();
        
        // Example: Get user profile

        Call<UserResponse> call = apiService.getUserProfile(userId);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update UI with API data
                    UserResponse userData = response.body();
                    userName = userData.getName();
                    setupUserProfile(); // Refresh profile
                } else {
                    // Handle error
                    Toast.makeText(getContext(), "Failed to load user data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Handle network error
                Toast.makeText(getContext(), "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

    /**
     * Setup user profile with avatar (first letter + random color)
     */
    private void setupUserProfile() {
        // Set user name
        tvUserName.setText(userName);
        
        // Get first letter
        String firstLetter = AvatarHelper.getFirstLetter(userName);
        tvAvatar.setText(firstLetter);
        
        // Get random color based on user name
        int avatarColor = AvatarHelper.getAvatarColor(userName);
        
        // Create circular background with color
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(avatarColor);
        tvAvatar.setBackground(drawable);
        
        // Set click listener for view profile
        tvViewProfile.setOnClickListener(v -> {
            // TODO: Navigate to profile page
            Toast.makeText(getContext(), "View Profile", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Setup settings RecyclerView with items
     */
    private void setupSettingsList() {
        // Create settings items
        List<SettingItem> settingItems = new ArrayList<>();
        settingItems.add(new SettingItem(
                getString(R.string.account_and_security), 
                android.R.drawable.ic_lock_lock,
                "account_security"
        ));
        settingItems.add(new SettingItem(
                getString(R.string.privacy), 
                android.R.drawable.ic_menu_view,
                "privacy"
        ));
        settingItems.add(new SettingItem(
                getString(R.string.app_information), 
                android.R.drawable.ic_menu_info_details,
                "app_info"
        ));

        // Setup adapter
        settingsAdapter = new SettingsAdapter(settingItems, item -> {
            // Handle setting item click
            handleSettingItemClick(item);
        });

        // Setup RecyclerView
        rvSettings.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSettings.setAdapter(settingsAdapter);
    }

    /**
     * Handle setting item click
     */
    private void handleSettingItemClick(SettingItem item) {
        String type = item.getType();
        View view = getView();
        if (view == null) return;
        
        switch (type) {
            case "account_security":
                // Navigate to Account and Security page
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_accountAndSecurityFragment);
                break;
            case "privacy":
                // Navigate to Privacy page
                Navigation.findNavController(view).navigate(R.id.action_settingsFragment_to_privacyFragment);
                break;
            case "app_info":
                // TODO: Navigate to App Information page
                Toast.makeText(getContext(), "App Information clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * Setup logout button
     */
    private void setupLogoutButton() {
        btnLogout.setOnClickListener(v -> {
            // Show confirmation dialog
            showLogoutConfirmation();
        });
    }

    /**
     * Show logout confirmation dialog
     */
    private void showLogoutConfirmation() {
        new android.app.AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.logout))
                .setMessage(getString(R.string.logout_confirmation))
                .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                    // TODO: Implement logout logic
                    performLogout();
                })
                .setNegativeButton(getString(R.string.no), null)
                .show();
    }

    /**
     * Perform logout action
     */
    private void performLogout() {
        // TODO: Clear user session, navigate to login screen, etc.
        Toast.makeText(getContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
        
        // Example: Navigate to login activity
        // Intent intent = new Intent(getContext(), LoginActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // startActivity(intent);
        // getActivity().finish();
    }
}
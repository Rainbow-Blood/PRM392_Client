package com.example.prm392_client.ui.settings.account;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.example.prm392_client.model.setting.AccountInfoItem;
import com.example.prm392_client.ui.settings.AvatarHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Account and Security Fragment
 * Displays user profile and account information (phone, email, password)
 */
public class AccountSecurityFragment extends Fragment {

    private TextView tvAvatar;
    private TextView tvUserName;
    private TextView tvPersonalInfo;
    private RecyclerView rvAccountInfo;
    private AccountSecurityAdapter accountInfoAdapter;

    // Sample data - Replace with API data
    private String userName = "Nguyễn Văn A";
    private String phoneNumber = "(+84) 999 999 999";
    private String email = "nguyenvana@gmail.com";

    public AccountSecurityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_and_security, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupUserProfile();
        setupAccountInfoList();
        setupNavigationListener();
        
        // Fetch user data from API
        fetchUserData();
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
                    if (destination.getId() == R.id.settingsFragment) {
                        NavDestination currentDest = controller.getCurrentDestination();
                        if (currentDest != null && 
                            currentDest.getId() == R.id.accountAndSecurityFragment) {
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
        tvAvatar = view.findViewById(R.id.tv_avatar);
        tvUserName = view.findViewById(R.id.tv_user_name);
        tvPersonalInfo = view.findViewById(R.id.tv_personal_info);
        rvAccountInfo = view.findViewById(R.id.rv_account_info);
    }

    private void setupUserProfile() {
        tvUserName.setText(userName);
        tvPersonalInfo.setText("Thông tin cá nhân");

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

        // Set click listener for profile card
        View profileCard = getView().findViewById(R.id.profile_card);
        if (profileCard != null && getContext() != null) {
            profileCard.setOnClickListener(v -> {
                // Navigate to Personal Info page
                Intent intent = new Intent(getContext(), PersonalInfoActivity.class);
                startActivity(intent);
            });
        }
    }

    private void setupAccountInfoList() {
        List<AccountInfoItem> accountInfoItems = new ArrayList<>();
        accountInfoItems.add(new AccountInfoItem(
                "Số điện thoại",
                phoneNumber,
                android.R.drawable.ic_menu_call,
                "phone"
        ));
        accountInfoItems.add(new AccountInfoItem(
                "Email",
                email,
                android.R.drawable.ic_dialog_email,
                "email"
        ));
        accountInfoItems.add(new AccountInfoItem(
                "Mật khẩu",
                "Nhấn để đổi mật khẩu",
                android.R.drawable.ic_lock_lock,
                "password"
        ));

        accountInfoAdapter = new AccountSecurityAdapter(accountInfoItems, item -> {
            handleAccountInfoClick(item);
        });

        rvAccountInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAccountInfo.setAdapter(accountInfoAdapter);
    }

    private void handleAccountInfoClick(AccountInfoItem item) {
        if (getContext() == null) return;
        
        String type = item.getType();
        switch (type) {
            case "phone":
                // TODO: Navigate to edit phone page
                Toast.makeText(getContext(), "Edit Phone Number", Toast.LENGTH_SHORT).show();
                break;
            case "email":
                // TODO: Navigate to edit email page
                Toast.makeText(getContext(), "Edit Email", Toast.LENGTH_SHORT).show();
                break;
            case "password":
                // Navigate to change password page
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void fetchUserData() {
        // TODO: Call API to fetch user data
        // Example:
        /*
        ApiService apiService = RetrofitClient.getApiService();
        Call<UserResponse> call = apiService.getUserProfile();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserResponse userData = response.body();
                    userName = userData.getName();
                    phoneNumber = userData.getPhone();
                    email = userData.getEmail();
                    setupUserProfile();
                    setupAccountInfoList();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }
}


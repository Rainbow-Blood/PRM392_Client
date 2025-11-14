package com.example.prm392_client.ui.settings.account;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.prm392_client.R;
import com.example.prm392_client.ui.settings.AvatarHelper;

/**
 * Personal Information Activity
 * Displays and allows editing of user personal information
 */
public class PersonalInfoActivity extends AppCompatActivity {

    private TextView tvAvatar;
    private TextView tvZaloName;
    private TextView tvDateOfBirth;
    private TextView tvGender;
    private Button btnEdit;

    // Sample data - Replace with API data
    private String userName = "Nguyễn Quốc Tuấn";
    private String dateOfBirth = "18/10/2003";
    private String gender = "Nam";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        setupToolbar();
        initViews();
        setupUserInfo();
        setupListeners();
        
        // Fetch user data from API
        fetchUserData();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Thông tin cá nhân");
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }

    private void initViews() {
        tvAvatar = findViewById(R.id.tv_avatar);
        tvZaloName = findViewById(R.id.tv_zalo_name);
        tvDateOfBirth = findViewById(R.id.tv_date_of_birth);
        tvGender = findViewById(R.id.tv_gender);
        btnEdit = findViewById(R.id.btn_edit);
    }

    private void setupUserInfo() {
        // Set user name
        TextView tvZaloNameValue = findViewById(R.id.tv_zalo_name_value);
        tvZaloNameValue.setText(userName);

        // Set date of birth
        TextView tvDateOfBirthValue = findViewById(R.id.tv_date_of_birth_value);
        tvDateOfBirthValue.setText(dateOfBirth);

        // Set gender
        TextView tvGenderValue = findViewById(R.id.tv_gender_value);
        tvGenderValue.setText(gender);

        // Setup avatar
        String firstLetter = AvatarHelper.getFirstLetter(userName);
        tvAvatar.setText(firstLetter);

        int avatarColor = AvatarHelper.getAvatarColor(userName);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(avatarColor);
        tvAvatar.setBackground(drawable);
    }

    private void setupListeners() {
        btnEdit.setOnClickListener(v -> {
            // TODO: Navigate to edit personal info page
            // Intent intent = new Intent(this, EditPersonalInfoActivity.class);
            // startActivity(intent);
            Toast.makeText(this, "Chức năng chỉnh sửa đang được phát triển", Toast.LENGTH_SHORT).show();
        });
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
                    dateOfBirth = userData.getDateOfBirth();
                    gender = userData.getGender();
                    setupUserInfo();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(PersonalInfoActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
        */
    }
}








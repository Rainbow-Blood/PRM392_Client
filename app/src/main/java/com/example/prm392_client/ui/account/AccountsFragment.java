package com.example.prm392_client.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.prm392_client.R;
import com.example.prm392_client.model.request.UpdateProfileRequest;
import com.example.prm392_client.model.response.GenericResponse;
import com.example.prm392_client.network.RetrofitClient;
import com.example.prm392_client.ui.auth.LoginActivity;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AccountsFragment extends Fragment {

    private EditText etName, etCity, etPhone, etDescription;
    private Button btnSaveChanges;
    private LinearLayout btnLogoutContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accounts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.etProfileName);
        etCity = view.findViewById(R.id.etProfileCity);
        etPhone = view.findViewById(R.id.etProfilePhone);
        etDescription = view.findViewById(R.id.etProfileDescription);
        btnSaveChanges = view.findViewById(R.id.btnSaveChanges);
        btnLogoutContainer = view.findViewById(R.id.btnLogoutContainer); 
        loadUserProfile();
        btnLogoutContainer.setOnClickListener(v -> handleLogout());
        btnSaveChanges.setOnClickListener(v -> handleUpdateProfile());
    }

    private void handleLogout() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("USER_TOKEN");
        editor.apply();

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        if (getActivity() != null) {
            getActivity().finish();
        }
    }
    private String getToken() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString("USER_TOKEN", "");
    }

    private void loadUserProfile() {
        RetrofitClient.getApiService().getMyProfile(getToken()).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    JsonObject profile = response.body();
                    if (profile.has("name")) etName.setText(profile.get("name").getAsString());
                    if (profile.has("city")) etCity.setText(profile.get("city").getAsString());
                    if (profile.has("phone")) etPhone.setText(profile.get("phone").getAsString());
                    if (profile.has("description")) etDescription.setText(profile.get("description").getAsString());
                } else {
                    Toast.makeText(getContext(), "Không thể tải thông tin cá nhân", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleUpdateProfile() {
        String name = etName.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (name.isEmpty()) {
            etName.setError("Tên không được để trống");
            return;
        }

        UpdateProfileRequest request = new UpdateProfileRequest(name, city, phone, description);

        RetrofitClient.getApiService().updateMyProfile(getToken(), request).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Cập nhật thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

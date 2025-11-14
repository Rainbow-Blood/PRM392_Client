package com.example.prm392_client.ui.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.prm392_client.R;
import com.example.prm392_client.model.request.LoginRequest;
import com.example.prm392_client.model.request.RegisterRequest;
import com.example.prm392_client.model.response.GenericResponse;
import com.example.prm392_client.model.response.LoginResponse;
import com.example.prm392_client.network.ApiService;
import com.example.prm392_client.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends Activity {
    private EditText etFullName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnSignUp;
    private TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_register);

        // Ánh xạ View
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etFullName = findViewById(R.id.etFullName);
        tvLoginLink = findViewById(R.id.tvLoginLink);
        btnSignUp = findViewById(R.id.btnSignUp); // <-- SỬA: Phải findViewById cho button

        // Sự kiện click cho nút Đăng ký
        btnSignUp.setOnClickListener(view -> {
            handleRegister();
        });

        // Sự kiện click cho link Đăng nhập
        tvLoginLink.setOnClickListener(view -> {
            // Quay về màn hình Login
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Đóng màn hình hiện tại
        });
    }

    private void handleRegister() {
        // Lấy và làm sạch dữ liệu đầu vào
        String email = etEmail.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // --- SỬA: Kiểm tra đầu vào đầy đủ hơn ---
        if (email.isEmpty() || fullName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        // --- SỬA: Dùng đúng Model và gọi đúng API ---
        RegisterRequest registerRequest = new RegisterRequest(email, fullName, password);

        ApiService apiService = RetrofitClient.getApiService();
        apiService.registerUser(registerRequest).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Đăng ký thành công! Vui lòng đăng nhập.", Toast.LENGTH_LONG).show();

                    // Chuyển về màn hình đăng nhập
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Đóng màn hình đăng ký
                } else {
                    // Xử lý lỗi từ server (ví dụ: email đã tồn tại)
                    Toast.makeText(SignUpActivity.this, "Đăng ký thất bại. Email có thể đã tồn tại.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                Log.e("SignUpActivity", "Lỗi kết nối: " + t.getMessage());
                Toast.makeText(SignUpActivity.this, "Lỗi kết nối. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


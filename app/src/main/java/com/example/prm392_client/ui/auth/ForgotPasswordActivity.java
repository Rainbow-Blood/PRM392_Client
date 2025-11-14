package com.example.prm392_client.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_client.R;
import com.example.prm392_client.model.response.GenericResponse;
import com.example.prm392_client.network.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmail;
    private Button btnSendCode;
    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_forgot_password);

        etEmail = findViewById(R.id.editTextEmail);
        btnSendCode = findViewById(R.id.btnSendLink);
        tvBackToLogin = findViewById(R.id.textViewBackToLogin);

        btnSendCode.setOnClickListener(v -> handleSendCode());

        tvBackToLogin.setOnClickListener(v -> {
            finish();
        });
    }

    private void handleSendCode() {
        String email = etEmail.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Vui lòng nhập email hợp lệ");
            etEmail.requestFocus();
            return;
        }


        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);


        RetrofitClient.getApiService().forgotPassword(requestBody).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Mã xác thực đã được gửi đến email của bạn.", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(ForgotPasswordActivity.this, VerifyCodeActivity.class);
                    intent.putExtra("USER_EMAIL", email);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Có lỗi xảy ra, vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                Log.e("ForgotPasswordActivity", "Lỗi: " + t.getMessage());
                Toast.makeText(ForgotPasswordActivity.this, "Lỗi kết nối mạng.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
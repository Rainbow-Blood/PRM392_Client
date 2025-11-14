package com.example.prm392_client.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392_client.R;
import com.example.prm392_client.model.request.VerifyCodeRequest;
import com.example.prm392_client.model.response.GenericResponse;
import com.example.prm392_client.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyCodeActivity extends AppCompatActivity {

    private EditText editTextCode;
    private Button btnVerify;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_verify_code);

        userEmail = getIntent().getStringExtra("USER_EMAIL");
        if (userEmail == null || userEmail.isEmpty()) {
            Toast.makeText(this, "Có lỗi xảy ra, không tìm thấy email", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        editTextCode = findViewById(R.id.editTextCode);
        btnVerify = findViewById(R.id.btnVerify);

        btnVerify.setOnClickListener(v -> handleVerification());
    }

    private void handleVerification() {
        String code = editTextCode.getText().toString().trim();

        if (code.length() < 6) {
            Toast.makeText(this, "Vui lòng nhập đủ 6 chữ số", Toast.LENGTH_SHORT).show();
            return;
        }

        VerifyCodeRequest request = new VerifyCodeRequest(userEmail, code);

        RetrofitClient.getApiService().verifyCode(request).enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(VerifyCodeActivity.this, "Xác thực thành công!", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(VerifyCodeActivity.this, ResetPasswordActivity.class);
                    intent.putExtra("USER_EMAIL", userEmail);
                    intent.putExtra("VERIFICATION_CODE", code);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(VerifyCodeActivity.this, "Mã xác thực không đúng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                Log.e("VerifyCodeActivity", "Lỗi: " + t.getMessage());
                Toast.makeText(VerifyCodeActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


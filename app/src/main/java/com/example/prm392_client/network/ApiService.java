package com.example.prm392_client.network;

import com.example.prm392_client.model.post.Post;
import com.example.prm392_client.model.request.LoginRequest;
import com.example.prm392_client.model.request.VerifyCodeRequest;
import com.example.prm392_client.model.response.GenericResponse;
import com.example.prm392_client.model.response.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/auth/login")
    public Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("api/auth/verify-code")
    Call<GenericResponse> verifyCode(@Body VerifyCodeRequest verifyCodeRequest);

    @POST("api/auth/reset-password")
    Call<GenericResponse> resetPassword(@Body LoginRequest resetPasswordRequest);


}

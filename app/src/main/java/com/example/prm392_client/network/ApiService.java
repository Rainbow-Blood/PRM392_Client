package com.example.prm392_client.network;

import com.example.prm392_client.model.request.LoginRequest;
import com.example.prm392_client.model.request.RegisterRequest;
import com.example.prm392_client.model.request.ResetPasswordRequest;
import com.example.prm392_client.model.request.UpdateProfileRequest;
import com.example.prm392_client.model.request.VerifyCodeRequest;
import com.example.prm392_client.model.response.GenericResponse;
import com.example.prm392_client.model.response.LoginResponse;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @POST("Auth/login")
    public Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("Auth/verify-code")
    Call<GenericResponse> verifyCode(@Body VerifyCodeRequest verifyCodeRequest);

    @POST("Auth/reset-password")
    Call<GenericResponse> resetPassword(@Body ResetPasswordRequest request);
    @POST("Auth/register")
    Call<GenericResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("Auth/forgot-password")
    Call<GenericResponse> forgotPassword(@Body Map<String, String> body);

    @GET("Member/profile")
    Call<JsonObject> getMyProfile(@Header("Authorization") String token);

    @PUT("Member/profile")
    Call<GenericResponse> updateMyProfile(@Header("Authorization") String token, @Body UpdateProfileRequest request);

}

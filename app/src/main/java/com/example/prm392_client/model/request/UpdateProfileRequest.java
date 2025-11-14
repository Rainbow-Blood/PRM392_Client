package com.example.prm392_client.model.request;

public class UpdateProfileRequest {
    private String name;
    private String city;
    private String phone;
    private String description;

    public UpdateProfileRequest(String name, String city, String phone, String description) {
        this.name = name;
        this.city = city;
        this.phone = phone;
        this.description = description;
    }
}
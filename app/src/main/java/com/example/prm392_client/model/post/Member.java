package com.example.prm392_client.model.post;

import com.google.gson.annotations.SerializedName;

public class Member {
    @SerializedName("Id")
    private String id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Email")
    private String email;


    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

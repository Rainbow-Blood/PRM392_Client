package com.example.prm392_client.model.contact;

import com.google.gson.annotations.SerializedName;

public class MemberDTO {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("city")
    private String city;

    // Constructor rỗng bắt buộc cho Gson
    public MemberDTO() {
    }

    // Constructor đầy đủ (tùy chọn)
    public MemberDTO(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    // Getters và Setters (cần thiết nếu trường là private)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
}

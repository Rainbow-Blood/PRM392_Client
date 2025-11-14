package com.example.prm392_client.model.contact;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Invitation {
    @SerializedName("id")
    private String Id;
    @SerializedName("toID")
    private String ToID;
    @SerializedName("fromID")
    private String FromID;
    @SerializedName("content")
    private String Content;
    @SerializedName("status")
    private String Status;

    @SerializedName("createdAt")
    private String CreatedAt;

    public Invitation() {
    }
    public Invitation(String id, String toID, String fromID, String content, String status, String createdAt) {
        Id = id;
        ToID = toID;
        FromID = fromID;
        Content = content;
        Status = status;
        CreatedAt = createdAt;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getToID() {
        return ToID;
    }

    public void setToID(String toID) {
        ToID = toID;
    }

    public String getFromID() {
        return FromID;
    }

    public void setFromID(String fromID) {
        FromID = fromID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }
}



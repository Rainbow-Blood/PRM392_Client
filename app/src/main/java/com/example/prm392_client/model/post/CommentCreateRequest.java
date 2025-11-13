package com.example.prm392_client.model.post;

import com.google.gson.annotations.SerializedName;

public class CommentCreateRequest {
    @SerializedName("postId") private String postId;
    @SerializedName("userId") private String userId; // hiện tại fix "1"
    @SerializedName("content") private String content;

    public CommentCreateRequest(String postId, String userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }
}

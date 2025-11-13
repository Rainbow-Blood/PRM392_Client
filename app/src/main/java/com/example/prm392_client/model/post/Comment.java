package com.example.prm392_client.model.post;

public class Comment {

    private String id;
    private String postId;
    private String userId;
    private String content;
    private String createdAt;

    public String getContent() { return content; }
    public String getUserId() { return userId; }
    public String getCreatedAt() { return createdAt; }
}

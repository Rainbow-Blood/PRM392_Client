package com.example.prm392_client.model.post;

public class Comment {
    private String id;
    private String ownerID;
    private String postID;
    private String content;
    private String name;
    private String status;
    private String createdAt;
    private String latestChange;

    public String getId() { return id; }
    public String getOwnerID() { return ownerID; }
    public String getPostID() { return postID; }
    public String getContent() { return content; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
    public String getLatestChange() { return latestChange; }
}

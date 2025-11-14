package com.example.prm392_client.model.post;

import com.google.gson.annotations.SerializedName;

public class CommentCreateRequest {
    private String postID;
    private String ownerID;
    private String content;

    public CommentCreateRequest(String postID, String ownerID, String content) {
        this.postID = postID;
        this.ownerID = ownerID;
        this.content = content;
    }
}

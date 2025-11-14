package com.example.prm392_client.model.post;

import com.google.gson.annotations.SerializedName;

public class PostCreateRequest {
        @SerializedName("userId")
        private String userId;

        @SerializedName("content")
        private String content;

        @SerializedName("visibility")
        private boolean visibility;

        public PostCreateRequest(String userId, String content, boolean visibility) {
            this.userId = userId;
            this.content = content;
            this.visibility = visibility;
        }
    }


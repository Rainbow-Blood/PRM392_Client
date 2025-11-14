package com.example.prm392_client.model.post;

import com.google.gson.annotations.SerializedName;

public class PostCreateRequest {
        @SerializedName("content")
        private String content;

        @SerializedName("visibility")
        private boolean visibility;

        public PostCreateRequest( String content, boolean visibility) {

            this.content = content;
            this.visibility = visibility;
        }
    }


package com.example.prm392_client.ui.messages.models;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Message {
    @SerializedName("id") public String Id;
    @SerializedName("ownerID") public String OwnerID;
    @SerializedName("conversationID") public String ConversationID;
    @SerializedName("content") public String Content;
    @SerializedName("createdAt") public Date CreatedAt;

    public Message() {}
}

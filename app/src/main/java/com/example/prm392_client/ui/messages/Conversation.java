package com.example.prm392_client.ui.messages;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Map;

public class Conversation {
    @SerializedName("id")
    public String Id;

    @SerializedName("name")
    public String Name;

    @SerializedName("type")
    public String Type;

    @SerializedName("ownerID")
    public String OwnerID;

    @SerializedName("status")
    public String Status;

    @SerializedName("groupMembers")
    public Map<String, MemberInfo> GroupMembers;

    @SerializedName("createdAt")
    public Date CreatedAt;

    // Helper: Get display name
    public String getDisplayName(String currentMemberId) {
        if ("Group".equalsIgnoreCase(Type)) {
            return Name != null && !Name.isEmpty() ? Name : "Group Chat";
        }

        // Friend chat: find the other member
        if (GroupMembers != null && GroupMembers.size() == 2) {
            for (Map.Entry<String, MemberInfo> entry : GroupMembers.entrySet()) {
                if (!entry.getKey().equals(currentMemberId)) {
                    return entry.getValue().Name != null && !entry.getValue().Name.isEmpty()
                            ? entry.getValue().Name
                            : "Unknown";
                }
            }
        }
        return "Chat";
    }
}

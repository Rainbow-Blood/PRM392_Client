package com.example.prm392_client.ui.messages.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.prm392_client.ui.messages.models.MemberInfo;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Conversation implements Parcelable {
    @SerializedName("id") public String Id;
    @SerializedName("name") public String Name;
    @SerializedName("type") public String Type;
    @SerializedName("ownerID") public String OwnerID;
    @SerializedName("status") public String Status;
    @SerializedName("groupMembers") public Map<String, MemberInfo> GroupMembers = new HashMap<>();
    @SerializedName("createdAt") public Date CreatedAt;

    public Conversation() {}

    protected Conversation(Parcel in) {
        Id = in.readString();
        Name = in.readString();
        Type = in.readString();
        OwnerID = in.readString();
        Status = in.readString();
        CreatedAt = in.readLong() != 0 ? new Date(in.readLong()) : null;
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            GroupMembers.put(in.readString(), in.readParcelable(MemberInfo.class.getClassLoader()));
        }
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id); dest.writeString(Name); dest.writeString(Type);
        dest.writeString(OwnerID); dest.writeString(Status);
        dest.writeLong(CreatedAt != null ? CreatedAt.getTime() : 0L);
        dest.writeInt(GroupMembers.size());
        for (Map.Entry<String, MemberInfo> e : GroupMembers.entrySet()) {
            dest.writeString(e.getKey());
            dest.writeParcelable(e.getValue(), flags);
        }
    }

    public static final Creator<Conversation> CREATOR = new Creator<Conversation>() {
        @Override public Conversation createFromParcel(Parcel in) { return new Conversation(in); }
        @Override public Conversation[] newArray(int size) { return new Conversation[size]; }
    };

    @Override public int describeContents() { return 0; }

    public String getOtherMemberName(String currentMemberId) {
        if ("Group".equalsIgnoreCase(Type)) {
            return Name != null && !Name.isEmpty() ? Name : "Group Chat";
        }
        for (Map.Entry<String, MemberInfo> e : GroupMembers.entrySet()) {
            if (!e.getKey().equals(currentMemberId)) {
                return e.getValue().Name != null && !e.getValue().Name.isEmpty() ? e.getValue().Name : "Unknown";
            }
        }
        return "Chat";
    }
}
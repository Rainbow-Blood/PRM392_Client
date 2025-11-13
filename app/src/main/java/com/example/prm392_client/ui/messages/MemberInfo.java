package com.example.prm392_client.ui.messages;

import com.google.gson.annotations.SerializedName;

public class MemberInfo {
    @SerializedName("name")
    public String Name;

    @SerializedName("nickName")
    public String NickName;

    @SerializedName("isIngroup")
    public boolean IsIngroup;
}

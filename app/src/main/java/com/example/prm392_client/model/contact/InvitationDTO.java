package com.example.prm392_client.model.contact;

import com.google.gson.annotations.SerializedName;

public class InvitationDTO{
    @SerializedName("toID")
    private String ToID;
    @SerializedName("fromID")
    private String FromID;
    @SerializedName("content")
    private String Content;

    public InvitationDTO() {
    }
    public InvitationDTO(String toID, String fromID, String content) {
        ToID = toID;
        FromID = fromID;
        Content = content;
    }

    public String getToID() {
        return ToID;
    }

    public void setToID(String toID) {
        ToID = toID;
    }

    public String getFromID() {
        return FromID;
    }

    public void setFromID(String fromID) {
        FromID = fromID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}

package com.example.prm392_client.model.contact;

public class InvitationDTO{
    private String ToID;
    private String FromID;
    private String Content;

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

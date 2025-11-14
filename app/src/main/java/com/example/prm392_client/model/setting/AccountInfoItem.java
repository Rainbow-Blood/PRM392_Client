package com.example.prm392_client.model.setting;

/**
 * Model class for account information items
 */
public class AccountInfoItem {
    private String title;
    private String value;
    private int iconResId;
    private String type; // "phone", "email", "password"

    public AccountInfoItem(String title, String value, int iconResId, String type) {
        this.title = title;
        this.value = value;
        this.iconResId = iconResId;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}








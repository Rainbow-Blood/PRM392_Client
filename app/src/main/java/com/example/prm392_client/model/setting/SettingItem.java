package com.example.prm392_client.model.setting;

/**
 * Model class for setting items
 */
public class SettingItem {
    private String title;
    private int iconResId;
    private String type; // "account_security", "privacy", "app_info"
    private boolean hasSwitch; // Whether this item has a switch
    private boolean switchChecked; // Switch state
    private boolean hasChip; // Whether this item has a chip button

    public SettingItem(String title, int iconResId, String type) {
        this.title = title;
        this.iconResId = iconResId;
        this.type = type;
        this.hasSwitch = false;
        this.switchChecked = false;
        this.hasChip = false;
    }

    public SettingItem(String title, int iconResId, String type, boolean hasSwitch, boolean switchChecked) {
        this.title = title;
        this.iconResId = iconResId;
        this.type = type;
        this.hasSwitch = hasSwitch;
        this.switchChecked = switchChecked;
        this.hasChip = false;
    }

    public SettingItem(String title, int iconResId, String type, boolean hasSwitch, boolean switchChecked, boolean hasChip) {
        this.title = title;
        this.iconResId = iconResId;
        this.type = type;
        this.hasSwitch = hasSwitch;
        this.switchChecked = switchChecked;
        this.hasChip = hasChip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean hasSwitch() {
        return hasSwitch;
    }

    public void setHasSwitch(boolean hasSwitch) {
        this.hasSwitch = hasSwitch;
    }

    public boolean isSwitchChecked() {
        return switchChecked;
    }

    public void setSwitchChecked(boolean switchChecked) {
        this.switchChecked = switchChecked;
    }

    public boolean hasChip() {
        return hasChip;
    }

    public void setHasChip(boolean hasChip) {
        this.hasChip = hasChip;
    }
}


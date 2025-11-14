package com.example.prm392_client.ui.settings.privacy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;
import com.example.prm392_client.model.setting.SettingItem;

import java.util.List;

/**
 * Adapter for Privacy Settings RecyclerView
 */
public class PrivacyAdapter extends RecyclerView.Adapter<PrivacyAdapter.SettingViewHolder> {

    private List<SettingItem> settingItems;
    private OnSettingItemClickListener listener;

    public interface OnSettingItemClickListener {
        void onSettingItemClick(SettingItem item);
    }

    public PrivacyAdapter(List<SettingItem> settingItems, OnSettingItemClickListener listener) {
        this.settingItems = settingItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SettingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_setting, parent, false);
        return new SettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingViewHolder holder, int position) {
        SettingItem item = settingItems.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.ivIcon.setImageResource(item.getIconResId());
        
        // Show/hide switch or arrow based on item type
        if (item.hasSwitch()) {
            // Show toggle switch
            holder.switchSetting.setVisibility(View.VISIBLE);
            holder.ivArrow.setVisibility(View.GONE);
            holder.switchSetting.setChecked(item.isSwitchChecked());
            holder.switchSetting.setOnCheckedChangeListener((buttonView, isChecked) -> {
                item.setSwitchChecked(isChecked);
                if (listener != null) {
                    listener.onSettingItemClick(item);
                }
            });
            // Disable click on itemView when switch is present
            holder.itemView.setOnClickListener(null);
            holder.itemView.setClickable(false);
        } else {
            // Show arrow for regular items
            holder.switchSetting.setVisibility(View.GONE);
            holder.ivArrow.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onSettingItemClick(item);
                }
            });
            holder.itemView.setClickable(true);
        }
        
        // Hide divider for last item
        if (position == settingItems.size() - 1) {
            holder.divider.setVisibility(View.GONE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return settingItems != null ? settingItems.size() : 0;
    }

    static class SettingViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
        ImageView ivArrow;
        SwitchCompat switchSetting;
        View divider;

        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_setting_icon);
            tvTitle = itemView.findViewById(R.id.tv_setting_title);
            ivArrow = itemView.findViewById(R.id.iv_arrow);
            switchSetting = itemView.findViewById(R.id.switch_setting);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}


package com.example.prm392_client.ui.settings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;
import com.example.prm392_client.model.setting.SettingItem;

import java.util.List;

/**
 * Adapter for Settings RecyclerView
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingViewHolder> {

    private List<SettingItem> settingItems;
    private OnSettingItemClickListener listener;

    public interface OnSettingItemClickListener {
        void onSettingItemClick(SettingItem item);
    }

    public SettingsAdapter(List<SettingItem> settingItems, OnSettingItemClickListener listener) {
        this.settingItems = settingItems;
        this.listener = listener;
    }

    public SettingsAdapter(List<SettingItem> settingItems) {
        this(settingItems, null);
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
        
        // Hide divider for last item
        if (position == settingItems.size() - 1) {
            holder.divider.setVisibility(View.GONE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSettingItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingItems != null ? settingItems.size() : 0;
    }

    static class SettingViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
        ImageView ivArrow;
        View divider;

        public SettingViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_setting_icon);
            tvTitle = itemView.findViewById(R.id.tv_setting_title);
            ivArrow = itemView.findViewById(R.id.iv_arrow);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}


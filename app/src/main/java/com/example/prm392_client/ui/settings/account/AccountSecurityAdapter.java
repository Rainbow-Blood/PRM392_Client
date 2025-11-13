package com.example.prm392_client.ui.settings.account;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;
import com.example.prm392_client.model.setting.AccountInfoItem;

import java.util.List;

/**
 * Adapter for Account Security RecyclerView
 */
public class AccountSecurityAdapter extends RecyclerView.Adapter<AccountSecurityAdapter.AccountInfoViewHolder> {

    private List<AccountInfoItem> accountInfoItems;
    private OnAccountInfoItemClickListener listener;

    public interface OnAccountInfoItemClickListener {
        void onAccountInfoItemClick(AccountInfoItem item);
    }

    public AccountSecurityAdapter(List<AccountInfoItem> accountInfoItems, OnAccountInfoItemClickListener listener) {
        this.accountInfoItems = accountInfoItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AccountInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_account_info, parent, false);
        return new AccountInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountInfoViewHolder holder, int position) {
        AccountInfoItem item = accountInfoItems.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvValue.setText(item.getValue());
        holder.ivIcon.setImageResource(item.getIconResId());

        // Hide divider for last item
        if (position == accountInfoItems.size() - 1) {
            holder.divider.setVisibility(View.GONE);
        } else {
            holder.divider.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAccountInfoItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return accountInfoItems != null ? accountInfoItems.size() : 0;
    }

    static class AccountInfoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
        TextView tvValue;
        ImageView ivArrow;
        View divider;

        public AccountInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_account_info_icon);
            tvTitle = itemView.findViewById(R.id.tv_account_info_title);
            tvValue = itemView.findViewById(R.id.tv_account_info_value);
            ivArrow = itemView.findViewById(R.id.iv_account_info_arrow);
            divider = itemView.findViewById(R.id.divider_account_info);
        }
    }
}


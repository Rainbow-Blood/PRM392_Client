package com.example.prm392_client.ui.messages;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.VH> {

    private List<Conversation> list = new ArrayList<>();
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private final String currentMemberId;

    private static final int[] COLORS = {
            0xFFE91E63, 0xFF9C27B0, 0xFF673AB7, 0xFF3F51B5,
            0xFF2196F3, 0xFF00BCD4, 0xFF009688, 0xFF4CAF50,
            0xFFCDDC39, 0xFFFF9800, 0xFFFF5722, 0xFF795548
    };

    public ConversationAdapter(String currentMemberId) {
        this.currentMemberId = currentMemberId;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Conversation> data) {
        list.clear();
        if (data != null) list.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() { return list.size(); }

    class VH extends RecyclerView.ViewHolder {
        TextView tvAvatar, tvName, tvType, tvTime;

        VH(@NonNull View v) {
            super(v);
            tvAvatar = v.findViewById(R.id.tvAvatar);
            tvName = v.findViewById(R.id.tvName);
            tvType = v.findViewById(R.id.tvType);
            tvTime = v.findViewById(R.id.tvTime);

            v.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Conversation c = list.get(position);
                    android.util.Log.d("CHAT_CLICK", "Conversation ID: " + c.Id);
                }
            });
        }

        void bind(Conversation c) {
            String displayName = getDisplayName(c);
            String firstLetter = displayName.isEmpty() ? "?" : displayName.substring(0, 1).toUpperCase();

            // Avatar: chữ cái + màu
            tvAvatar.setText(firstLetter);
            int color = COLORS[Math.abs(c.Id.hashCode()) % COLORS.length];
            tvAvatar.setBackgroundColor(color);

            // Tên
            tvName.setText(displayName);
            tvType.setText(c.Type);

            // Thời gian
            tvTime.setText(c.CreatedAt != null ? sdf.format(c.CreatedAt) : "");
        }

        private String getDisplayName(Conversation c) {
            if ("Group".equalsIgnoreCase(c.Type)) {
                return c.Name != null && !c.Name.isEmpty() ? c.Name : "Group Chat";
            }

            // Friend: tìm người kia
            if (c.GroupMembers != null) {
                for (Map.Entry<String, MemberInfo> e : c.GroupMembers.entrySet()) {
                    if (!e.getKey().equals(currentMemberId)) {
                        return e.getValue().Name != null && !e.getValue().Name.isEmpty()
                                ? e.getValue().Name : "Unknown";
                    }
                }
            }
            return "Chat";
        }
    }
}
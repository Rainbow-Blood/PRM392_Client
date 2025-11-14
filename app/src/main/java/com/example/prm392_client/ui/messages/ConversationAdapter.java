package com.example.prm392_client.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm392_client.R;
import com.example.prm392_client.ui.messages.models.Conversation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.VH> {

    private final List<Conversation> list = new ArrayList<>();
    private final String currentMemberId;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static final int[] COLORS = {0xFFE91E63, 0xFF9C27B0, 0xFF673AB7, 0xFF3F51B5,
            0xFF2196F3, 0xFF00BCD4, 0xFF009688, 0xFF4CAF50, 0xFFCDDC39, 0xFFFF9800, 0xFFFF5722, 0xFF795548};

    public ConversationAdapter(String currentMemberId) {
        this.currentMemberId = currentMemberId;
    }

    public void setData(List<Conversation> data) {
        list.clear();
        if (data != null) list.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(list.get(position));
    }

    @Override public int getItemCount() { return list.size(); }

    class VH extends RecyclerView.ViewHolder {
        TextView tvAvatar, tvName, tvType, tvTime;

        VH(@NonNull View v) {
            super(v);
            tvAvatar = v.findViewById(R.id.tvAvatar);
            tvName = v.findViewById(R.id.tvName);
            tvType = v.findViewById(R.id.tvType);
            tvTime = v.findViewById(R.id.tvTime);

            v.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                if (pos == RecyclerView.NO_POSITION) return;
                Bundle b = new Bundle();
                b.putParcelable("conversation", list.get(pos));
                Navigation.findNavController(view)
                        .navigate(R.id.action_messagesFragment_to_chatFragment, b);
            });
        }

        void bind(Conversation c) {
            String name = c.getOtherMemberName(currentMemberId);
            String letter = name.isEmpty() ? "?" : name.substring(0, 1).toUpperCase();

            tvAvatar.setText(letter);
            tvAvatar.setBackgroundColor(COLORS[Math.abs(c.Id.hashCode()) % COLORS.length]);
            tvName.setText(name);
            tvType.setText(c.Type);
            tvTime.setText(c.CreatedAt != null ? sdf.format(c.CreatedAt) : "");
        }
    }
}
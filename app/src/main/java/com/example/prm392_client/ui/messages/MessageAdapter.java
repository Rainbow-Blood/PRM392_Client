package com.example.prm392_client.ui.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm392_client.R;
import com.example.prm392_client.ui.messages.models.Message;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.VH> {

    private static final int SENT = 1, RECEIVED = 2;
    private final List<Message> list = new ArrayList<>();
    private final String currentMemberId;
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

    public MessageAdapter(String currentMemberId) {
        this.currentMemberId = currentMemberId;
    }

    public void setData(List<Message> data) {
        list.clear();
        if (data != null) list.addAll(data);
        notifyDataSetChanged();
    }

    public void addMessage(Message m) {
        list.add(m);
        notifyItemInserted(list.size() - 1);
    }

    @Override public int getItemViewType(int pos) {
        return list.get(pos).OwnerID.equals(currentMemberId) ? SENT : RECEIVED;
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = viewType == SENT ? R.layout.item_message_sent : R.layout.item_message_received;
        return new VH(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
    }

    @Override public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(list.get(position));
    }

    @Override public int getItemCount() { return list.size(); }

    class VH extends RecyclerView.ViewHolder {
        TextView tvContent, tvTime;

        VH(@NonNull View v) {
            super(v);
            tvContent = v.findViewById(R.id.tvContent);
            tvTime = v.findViewById(R.id.tvTime);
        }

        void bind(Message m) {
            tvContent.setText(m.Content);
            if (tvTime != null && m.CreatedAt != null) {
                tvTime.setText(sdf.format(m.CreatedAt));
            }
        }
    }\
}
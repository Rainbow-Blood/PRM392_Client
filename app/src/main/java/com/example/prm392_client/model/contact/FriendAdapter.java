package com.example.prm392_client.model.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm392_client.R;
import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
    private List<MemberDTO> friendsList;

    public FriendAdapter(List<MemberDTO> friendsList) {
        this.friendsList = friendsList;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_friend_view, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        MemberDTO friend = friendsList.get(position);
        holder.tvName.setText(friend.getName());
        holder.tvCity.setText(friend.getCity());
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    public void updateData(List<MemberDTO> newFriends) {
        this.friendsList = newFriends;
        notifyDataSetChanged();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCity;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_friend_name);
            tvCity = itemView.findViewById(R.id.tv_friend_city);
        }
    }
}
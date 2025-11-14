package com.example.prm392_client.ui.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;
import com.example.prm392_client.model.post.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    private List<Comment> comments = new ArrayList<>();

    public void setData(List<Comment> data) {
        comments.clear();
        if (data != null) comments.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment c = comments.get(position);
        holder.user.setText(c.getOwnerID());
        holder.content.setText(c.getContent());
        holder.date.setText(DateHelper.formatCreatedDate(c.getCreatedAt()));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView user, content, date;
        CommentViewHolder(View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.tvCommentUser);
            content = itemView.findViewById(R.id.tvContent);
            date = itemView.findViewById(R.id.tvCommentDate);
        }
    }
}

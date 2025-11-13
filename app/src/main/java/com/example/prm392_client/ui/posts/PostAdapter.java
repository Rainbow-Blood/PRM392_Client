package com.example.prm392_client.ui.posts;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.prm392_client.R;
import com.example.prm392_client.model.post.Post;

import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> posts;

    public PostAdapter(List<Post> posts){
        this.posts = posts;
    }


    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.tvUsername.setText(post.getOwnerID());
        holder.tvCreatedDate.setText(post.getCreatedAt());
        holder.tvContent.setText(post.getContent());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder{
        TextView tvUsername, tvCreatedDate, tvContent;
        Button btnComment;

        public PostViewHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvCreatedDate = itemView.findViewById(R.id.tvCreatedDate);
            tvContent = itemView.findViewById(R.id.tvContent);
            btnComment = itemView.findViewById(R.id.btnComment);
        }


    }
}

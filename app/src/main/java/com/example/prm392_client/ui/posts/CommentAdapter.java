package com.example.prm392_client.ui.posts;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.model.post.Comment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    private List<Comment> comments;

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }
}

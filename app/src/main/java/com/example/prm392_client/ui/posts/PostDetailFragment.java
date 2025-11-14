package com.example.prm392_client.ui.posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;
import com.example.prm392_client.model.post.Comment;
import com.example.prm392_client.model.post.CommentCreateRequest;
import com.example.prm392_client.model.post.Post;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailFragment extends Fragment {
    private TextView tvUser, tvDate, tvContent;
    private RecyclerView rvComments;
    private EditText etComment;
    private Button btnSend, btnBack;
    private CommentAdapter commentAdapter;
    private String postId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);

        tvUser = view.findViewById(R.id.tvDetailUsername);
        tvDate = view.findViewById(R.id.tvDetailCreatedDate);
        tvContent = view.findViewById(R.id.tvDetailContent);
        rvComments = view.findViewById(R.id.rvComments);
        etComment = view.findViewById(R.id.etComment);
        btnSend = view.findViewById(R.id.btnSendComment);
        btnBack = view.findViewById(R.id.btnBack);

        commentAdapter = new CommentAdapter();
        rvComments.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvComments.setAdapter(commentAdapter);

        postId = getArguments() != null ? getArguments().getString("postId") : null;
        if (postId == null) {
            Toast.makeText(getContext(), "Thiếu postId", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
            return view;
        }

        loadPostDetail();
        loadComments();

        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());
        btnSend.setOnClickListener(v -> sendComment());

        return view;
    }

    private void loadPostDetail() {
        PostApi api = RetrofitClient.getApi();
        api.getPostById(postId).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> resp) {
                Post p = resp.body();
                if (resp.isSuccessful() && p != null) {
                    tvUser.setText(p.getOwnerID());
                    tvDate.setText(DateHelper.formatCreatedDate(p.getCreatedAt()));
                    tvContent.setText(p.getContent());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Lỗi mạng: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadComments() {
        PostApi api = RetrofitClient.getApi();
        api.getComments(postId).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(@NonNull Call<List<Comment>> call, @NonNull Response<List<Comment>> resp) {
                if (resp.isSuccessful() && resp.body() != null) {
                    commentAdapter.setData(resp.body());
                } else {
                    commentAdapter.setData(Collections.emptyList());
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Comment>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Lỗi tải bình luận: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendComment() {
        String content = etComment.getText().toString().trim();
        if (content.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập bình luận", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = "1";

        PostApi api = RetrofitClient.getApi();
        CommentCreateRequest req = new CommentCreateRequest(postId, userId, content);

        btnSend.setEnabled(false);
        api.createComment(req).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(@NonNull Call<Comment> call, @NonNull Response<Comment> resp) {
                btnSend.setEnabled(true);
                if (resp.isSuccessful() && resp.body() != null) {
                    etComment.setText("");
                    Toast.makeText(getContext(), "Gửi bình luận thành công", Toast.LENGTH_SHORT).show();
                    loadComments();
                    rvComments.scrollToPosition(commentAdapter.getItemCount() - 1);
                } else {
                    Toast.makeText(getContext(), "Gửi bình luận thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Comment> call, @NonNull Throwable t) {
                btnSend.setEnabled(true);
                Toast.makeText(getContext(), "Lỗi mạng: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

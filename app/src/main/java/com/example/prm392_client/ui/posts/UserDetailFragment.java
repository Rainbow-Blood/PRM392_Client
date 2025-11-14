package com.example.prm392_client.ui.posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_client.R;
import com.example.prm392_client.model.post.Member;
import com.example.prm392_client.model.post.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailFragment extends Fragment {
    private TextView tvUserName, tvUserDescription;
    private RecyclerView rvUserPosts;
    private PostAdapter adapter;
    private String userId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_user, container, false);

        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserDescription = view.findViewById(R.id.tvUserDescription);
        rvUserPosts = view.findViewById(R.id.rvUserPosts);

        rvUserPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostAdapter(new ArrayList<>());
        rvUserPosts.setAdapter(adapter);

        userId = getArguments() != null ? getArguments().getString("userId") : null;
        if (userId == null) {
            Toast.makeText(getContext(), "Thiếu userId", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
            return view;
        }

        loadUserInfo();
        loadUserPosts();

        return view;
    }

    private void loadUserInfo() {
        PostApi api = RetrofitClient.getApi();
        api.getUserById(userId).enqueue(new Callback<Member>() {
            @Override
            public void onResponse(Call<Member> call, Response<Member> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Member user = response.body();
                    tvUserName.setText(user.getName());
                }
            }

            @Override
            public void onFailure(Call<Member> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi tải thông tin người dùng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserPosts() {
        PostApi api = RetrofitClient.getApi();
        api.getPostsByUserId(userId).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
//                    adapter.setData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi tải bài viết", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

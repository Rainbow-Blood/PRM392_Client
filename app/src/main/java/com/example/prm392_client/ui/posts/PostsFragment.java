package com.example.prm392_client.ui.posts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.prm392_client.R;
import com.example.prm392_client.model.post.Post;
import com.example.prm392_client.network.ApiService;
import com.example.prm392_client.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsFragment extends Fragment {

    private RecyclerView rvPosts;
    private EditText etCreatePost;
    private PostAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        rvPosts = view.findViewById(R.id.rvPosts);
        etCreatePost = view.findViewById(R.id.etCreatePost);

        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        loadPosts();
        return view;
    }

    private void loadPosts() {
        ApiService api = RetrofitClient.getApiService();
        api.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new PostAdapter(response.body());
                    rvPosts.setAdapter(adapter);
                } else {
                    // Có thể log lỗi hoặc hiển thị Toast
                    Log.e("PostsFragment", "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
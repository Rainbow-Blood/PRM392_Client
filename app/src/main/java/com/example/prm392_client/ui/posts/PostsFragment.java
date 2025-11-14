package com.example.prm392_client.ui.posts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prm392_client.R;
import com.example.prm392_client.model.post.Post;
import com.example.prm392_client.model.post.Member;

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

        etCreatePost.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Mở trang tạo bài viết", Toast.LENGTH_SHORT).show();
            //TODO: mở activity/fragment tạo bài viết
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_postsFragment_to_createPostFragment);
        });

        loadPosts();
        return view;
    }

    private void loadPosts() {
        PostApi api = RetrofitClient.getApi();
        api.getAllPostsPublic().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Post> posts = response.body();

                    // Với mỗi post, gọi API lấy tên user theo OwnerID
                    for (Post post : posts) {
                        api.getUserById(post.getOwnerID()).enqueue(new Callback<Member>() {
                            @Override
                            public void onResponse(Call<Member> call, Response<Member> resp) {
                                if (resp.isSuccessful() && resp.body() != null) {
                                    post.setOwnerName(resp.body().getName()); // thêm field ownerName vào Post.java
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            @Override
                            public void onFailure(Call<Member> call, Throwable t) {
                                // giữ nguyên OwnerID nếu lỗi
                            }
                        });
                    }

                    adapter = new PostAdapter(posts);
                    rvPosts.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Không tải được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối server", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
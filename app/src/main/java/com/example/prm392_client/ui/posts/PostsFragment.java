package com.example.prm392_client.ui.posts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prm392_client.R;
import com.example.prm392_client.model.post.Post;

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
                    Log.e("PostsFragment", "Sổ lượng posts trong list: "+ response.body().size());
                    adapter = new PostAdapter(response.body());
                    rvPosts.setAdapter(adapter);
                } else {
                    Log.e("PostsFragment", "Response not successful: " + response.code());
                    Toast.makeText(getContext(), "Không tải được dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("PostsFragment", "Error: " + t.getMessage());
                Toast.makeText(getContext(), "Lỗi kết nối server", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
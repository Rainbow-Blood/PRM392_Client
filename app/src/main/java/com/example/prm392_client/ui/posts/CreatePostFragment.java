package com.example.prm392_client.ui.posts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import androidx.fragment.app.Fragment;

import com.example.prm392_client.R;
import com.example.prm392_client.model.post.Post;
import com.example.prm392_client.model.post.PostCreateRequest;
import com.google.gson.JsonObject;

public class CreatePostFragment extends Fragment {
    private EditText etPostContent;
    private Spinner spVisibility;
    private Button btnPost, btnBack;


    private String getToken() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
        return "Bearer " + sharedPreferences.getString("USER_TOKEN", "");
    }




    // Optional: để biết quay về đâu (posts list / user detail)
    private static final String ARG_SOURCE = "source";
    public static CreatePostFragment newInstance(String source){
        CreatePostFragment f = new CreatePostFragment();
        Bundle b = new Bundle();
        b.putString(ARG_SOURCE, source);
        f.setArguments(b);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        etPostContent = view.findViewById(R.id.etPostContent);
        spVisibility = view.findViewById(R.id.spVisibility);
        btnPost = view.findViewById(R.id.btnPost);
        btnBack = view.findViewById(R.id.btnBack);


       setupVisibilitySpinner();
       setupActions();
       return view;
    }

    private void setupVisibilitySpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                new String[]{"Công khai", "Riêng tư"}
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spVisibility.setAdapter(adapter);
    }


    private void setupActions() {
        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());

        btnPost.setOnClickListener(v -> {
            String content = etPostContent.getText().toString().trim();
            boolean visibility = "Công khai".equals(spVisibility.getSelectedItem().toString());

            if (content.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập nội dung bài viết", Toast.LENGTH_SHORT).show();
                return;
            }
            createPost(content, visibility);
        });
    }

    private void setLoading(boolean loading) {
        btnPost.setEnabled(!loading);
        btnBack.setEnabled(!loading);
        spVisibility.setEnabled(!loading);
        etPostContent.setEnabled(!loading);
    }



    private void createPost(String content, boolean visibility) {
        // TODO: Sau này lấy userId từ SharedPreferences sau khi login
//         SharedPreferences prefs = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE);
//         String userId = prefs.getString("userId", "");



        PostApi api = RetrofitClient.getApi();
        PostCreateRequest request = new PostCreateRequest(content, visibility);
        api.createPost(getToken(), request).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(getContext(), "Đăng bài thành công", Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getContext(), "Đăng bài thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}


package com.example.prm392_client.ui.posts;

import com.example.prm392_client.model.post.Comment;
import com.example.prm392_client.model.post.CommentCreateRequest;
import com.example.prm392_client.model.post.Post;
import com.example.prm392_client.model.post.PostCreateRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface PostApi {


    @GET("api/Post/GetAllPosts")
    Call<List<Post>> getAllPosts();

    @GET("api/Post/GetAllPostsPublic")
    Call<List<Post>> getAllPostsPublic();


    @POST("api/Post/CreatePost")
    Call<Post> createPost(@Body PostCreateRequest post);

    @GET("api/Post/GetPostByPostId")
    Call<Post> getPostById(@Query("id") String id);

    @GET("api/Post/GetAllPostByUserId")
    Call<List<Post>> getPostsByUserId(@Query("ownerId") String ownerId);

    @GET("Post/{id}")
    Call<Post> getPostDetail(@Path("id") String id);

    @GET("Comment/Post/{postId}")
    Call<List<Comment>> getComments(@Path("postId") String postId);

    @POST("Comment")
    Call<Comment> createComment(@Body CommentCreateRequest request);




}

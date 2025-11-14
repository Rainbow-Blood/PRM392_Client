package com.example.prm392_client.ui.posts;

import com.example.prm392_client.model.post.Comment;
import com.example.prm392_client.model.post.CommentCreateRequest;
import com.example.prm392_client.model.post.Post;
import com.example.prm392_client.model.post.PostCreateRequest;
import com.example.prm392_client.model.post.Member;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface PostApi {


    @GET("api/Post/GetUserByUserId")
    Call<Member> getUserById(@Query("id") String id);

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


    @GET("api/Comment/post/{postId}")
    Call<List<Comment>> getComments(@Path("postId") String postId);

    @POST("api/Comment")
    Call<Comment> createComment(@Body CommentCreateRequest request);

    @PUT("api/Comment/{id}")
    Call<Void> updateComment(@Path("id") String id, @Body CommentCreateRequest request);

    @DELETE("api/Comment/{id}")
    Call<Void> deleteComment(@Path("id") String id);
}

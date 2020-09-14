package com.example.androidretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FastDostavka {
    @GET("/posts/{id}")
    public Call<Post> getPostWithID(@Path("id") int id);
    @GET("/posts")
    public Call<List<Post>> getAllPosts();
}

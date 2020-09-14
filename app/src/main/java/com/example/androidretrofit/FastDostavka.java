package com.example.androidretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FastDostavka {
    @POST("/api/auth/login")
    public Call<Post> login(@Body Login m);
//    @GET("/posts")
//    public Call<List<Post>> getAllPosts();
}

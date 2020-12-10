package com.example.githubfinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {
    public static final String BASE_URL= "https://api.github.com/";



    @GET("users/{user}")
    Call<User> getInfo(@Path("user") String username);

    @GET("/users/{user}/repos")
    Call<List<UserRepo>> getRepo(@Path("user") String username);

}

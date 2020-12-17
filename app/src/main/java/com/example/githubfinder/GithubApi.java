package com.example.githubfinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GithubApi {
    public static final String BASE_URL= "https://api.github.com/";

    @GET("/users/{user}")
    Call<User> getInfo(@Path("user") String username);

    @GET("/users/{user}/repos")
    Call<List<UserRepo>> getRepo(@Path("user") String username);

    @GET
    Call<String> getLanguages(@Url String url);
    @GET
    Call<List<Contributors>> getContributors(@Url String url);

}

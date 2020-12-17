package com.example.githubfinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoApi {
    public static final String REPO_URL="https://api.github.com/users/{user}/repos/";
    @GET("users/{user}/repos")
    Call<List<UserRepo>> getRepo(@Path("user") String username);

}

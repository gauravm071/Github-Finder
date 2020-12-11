package com.example.githubfinder;

import java.util.List;

import retrofit2.Response;

public interface RepoApiCallback {
    void onSuccess(Response<List<UserRepo>> response);
    void onFailure();
}

package com.example.githubfinder;

import retrofit2.Response;

public interface ApiCallBack {
    void onSuccess(Response<User> response);
    void onFailure();
    void onError();
}

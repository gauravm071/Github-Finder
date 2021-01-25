package com.example.githubfinder;

import android.annotation.SuppressLint;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface Repository {
    public static void getUserDetails(String username, ApiCallBack apiCallBack){
        GithubApi githubApi= getRetrofit.getInstance(GithubApi.BASE_URL).create(GithubApi.class);
        Call<User> call= githubApi.getInfo(username);
        call.enqueue(new Callback<User>(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body()!=null && response.body()!=null && response.code()==200){
                    apiCallBack.onSuccess(response);
                }
                else{
                    apiCallBack.onError();
                }

            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                apiCallBack.onFailure();
            }
        });
    }

    public static void getAllRepo(String username,RepoApiCallback repoApiCallback){
        GithubApi githubApi= getRetrofit.getInstance(GithubApi.BASE_URL).create(GithubApi.class);
        Call<List<UserRepo>> listCall= githubApi.getRepo(username);
        listCall.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {
                if(response.body()!=null && response.body()!=null && response.code()==200){
                    repoApiCallback.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                repoApiCallback.onFailure();
            }
        });
    }

}

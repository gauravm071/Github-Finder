package com.example.githubfinder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class getRetrofit {
    private static Retrofit retrofit=null;
    public static Retrofit getInstance(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder().baseUrl(GithubApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}

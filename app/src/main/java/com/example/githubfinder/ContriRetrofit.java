package com.example.githubfinder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ContriRetrofit {
    private static Retrofit contriRetrofit= null;
    public static Retrofit getInstance(String url){
        if(contriRetrofit==null){
            contriRetrofit= new Retrofit.Builder().baseUrl(url+"/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return contriRetrofit;
    }
}

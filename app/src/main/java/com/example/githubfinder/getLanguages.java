package com.example.githubfinder;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class getLanguages {
    private static Retrofit langRetrofit= null;
    public static Retrofit getInstance(String url){
       if(langRetrofit==null){
           langRetrofit = new Retrofit.Builder().baseUrl(url+"/").addConverterFactory(ScalarsConverterFactory.create()).build();
       }
      return langRetrofit;
    }

}

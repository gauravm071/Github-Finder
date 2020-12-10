package com.example.githubfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {
    TextView name, email, publicRepos, followers, following;
    ImageView userDp;
    RecyclerView recyclerView;
    List<UserRepo> listOfRepo= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name= findViewById(R.id.name);
        email= findViewById(R.id.email);
        publicRepos= findViewById(R.id.publicrepos);
        followers= findViewById(R.id.followers);
        following= findViewById(R.id.followings);
        userDp= findViewById(R.id.userDp);
        recyclerView= findViewById(R.id.recyclerView);
        Intent intent= getIntent();
        String username= intent.getStringExtra("username");
        Retrofit retrofit= new Retrofit.Builder().baseUrl(GithubApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        GithubApi githubApi= retrofit.create(GithubApi.class);
        Call<User> call= githubApi.getInfo(username);
        call.enqueue(new Callback<User>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body()!=null){
                    User user= response.body();
                    name.setText("Name : "+user.getName());
                    email.setText("Email : "+user.getEmail());
                    followers.setText("Followers : "+user.followers);
                    following.setText("Following : "+user.getFollowings());
                    publicRepos.setText("Public Repo : "+user.getPublicRepos());
                    Picasso.with(UserActivity.this).load(user.getImageUrl()).into(userDp);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong Username ",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed to get Data",Toast.LENGTH_LONG).show();
            }
        });

        Call<List<UserRepo>> listCall= githubApi.getRepo(username);
        listCall.enqueue(new Callback<List<UserRepo>>() {
            @Override
            public void onResponse(Call<List<UserRepo>> call, Response<List<UserRepo>> response) {
                if(response.body()!=null){
                    listOfRepo.addAll(response.body());
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(new RepoAdapter(listOfRepo));
            }

            @Override
            public void onFailure(Call<List<UserRepo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Could Not Find Repo",Toast.LENGTH_LONG).show();
            }
        });

    }
}
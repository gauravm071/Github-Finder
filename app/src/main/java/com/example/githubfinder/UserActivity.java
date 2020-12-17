package com.example.githubfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity implements RepoAdapterInterface, Serializable {
    TextView name, email, publicRepos, followers, following;
    ImageView userDp;
    RecyclerView recyclerView;
    List<UserRepo> listOfRepo = new ArrayList<>();
    LottieAnimationView lottieAnimationView, repoloader;
    CardView cardView, infocard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        publicRepos = findViewById(R.id.publicrepos);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.followings);
        userDp = findViewById(R.id.userDp);
        recyclerView = findViewById(R.id.recyclerView);
        lottieAnimationView = findViewById(R.id.lottie);
        repoloader = findViewById(R.id.repoloader);
        cardView = findViewById(R.id.repoCard);
        infocard = findViewById(R.id.infocard);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        Repository.getUserDetails(username, new ApiCallBack() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(Response<User> response) {
                lottieAnimationView.cancelAnimation();
                lottieAnimationView.setVisibility(View.GONE);
                repoloader.setVisibility(View.VISIBLE);
                infocard.setVisibility(View.VISIBLE);
                User user = response.body();
                name.setText("Name : " + user.getName());
                email.setText("Email : " + user.getEmail());
                followers.setText("Followers : " + user.followers);
                following.setText("Following : " + user.getFollowings());
                publicRepos.setText("Public Repo : " + user.getPublicRepos());
                Picasso.with(UserActivity.this).load(user.getImageUrl()).into(userDp);
            }

            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onError() {
                Toast.makeText(getApplicationContext(), "Wrong Username", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Repository.getAllRepo(username, new RepoApiCallback() {
            @Override
            public void onSuccess(Response<List<UserRepo>> response) {
                repoloader.cancelAnimation();
                repoloader.setVisibility(View.GONE);
                cardView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                listOfRepo.addAll(response.body());
                setAdapter();
            }

            @Override
            public void onFailure() {
                Toast.makeText(getApplicationContext(), "Failed to Get Repository Data", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    public void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RepoAdapter(this::onClick, listOfRepo));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", false).apply();
        editor.commit();
        finish();
        return true;
    }


    @Override
    public void onClick(int position) {
        UserRepo userRepo = listOfRepo.get(position);
        Intent intent = new Intent(getApplicationContext(), RepoActivity.class);
        Gson gson = new Gson();
        String user = gson.toJson(userRepo);
        intent.putExtra("userRepo", user);
        startActivity(intent);
    }
}
package com.example.githubfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoActivity extends AppCompatActivity {
    TextView repoName,repodesc,language,forkCount,defaultBranch,repoUrl,contributors,tv;
    ImageView imageView;
    final String[] myLanguages = {""};
    String myContributors="";
    List<Contributors> contributorsList= new ArrayList<>();
    UserRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        Intent intent= getIntent();
        String userRepo= intent.getStringExtra("userRepo");
        Gson gson= new Gson();
        repo= gson.fromJson(userRepo,UserRepo.class);

        repoName= findViewById(R.id.RepoName);
        repodesc= findViewById(R.id.RepoDesc);
        language= findViewById(R.id.RepoLanguage);
        contributors= findViewById(R.id.RepoContributor);
        forkCount= findViewById(R.id.forksCount);
        defaultBranch= findViewById(R.id.default_branch);
        repoUrl= findViewById(R.id.RepoUrl);
        imageView= findViewById(R.id.cat);
        imageView.setImageResource(R.drawable.github);
        repoUrl.setText(repo.getUrl());
        repoName.setText(repo.getName());
        forkCount.setText(repo.getForksCount());
        defaultBranch.setText(repo.getDefaultBranch());
        contributors.setMovementMethod(new ScrollingMovementMethod());
        if(repo.getDescription()==null){
            repodesc.setText("No Description Provided");
        }
        else {
            repodesc.setText(repo.getDescription());
            repodesc.setMovementMethod(new ScrollingMovementMethod());
        }

       if(repo.getLanguage()!=null){
           repolanguages();
       }
       else{
           language.setText("No Language Provided");
       }
       repoContributors();

    }
    void repolanguages(){
        GithubApi githubApi= getLanguages.getInstance(repo.getLanguagesurl()).create(GithubApi.class);
        Call<String> languagesCall= githubApi.getLanguages(repo.getLanguagesurl());
        languagesCall.enqueue(new Callback<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response!=null && response.body()!=null && response.code()==200 ){
                    String myResponse= response.body();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if(jsonObject!=null){
                            for(int i = 0; i<jsonObject.names().length(); i++){
//                        Log.v("RESPONSE", "key = " + jsonObject.names().getString(i) + " value = " + jsonObject.get(jsonObject.names().getString(i)));
                                myLanguages[0] += jsonObject.names().getString(i);
                                if(i<jsonObject.names().length()-1){
                                    myLanguages[0]+=", ";
                                }
                            }
                        }
                    }catch (JSONException err){
                        Log.d("Error", err.toString());
                    }
                }

                if(myLanguages[0].length()==0){
                    language.setText("No Language Provided");
                }
                else language.setText(myLanguages[0]);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    void repoContributors(){
        GithubApi githubApi= ContriRetrofit.getInstance(repo.getContributionUrl()).create(GithubApi.class);
        Call<List<Contributors>> contricall= githubApi.getContributors(repo.getContributionUrl());
        contricall.enqueue(new Callback<List<Contributors>>() {
            @Override
            public void onResponse(Call<List<Contributors>> call, Response<List<Contributors>> response) {
                Log.v("CONTRIRESPONSE",response.body().get(0).getLogin());
                contributorsList.addAll(response.body());
                for(int i=0;i<contributorsList.size();i++){
                    myContributors+=contributorsList.get(i).getLogin();
                    if(i<contributorsList.size()-1){
                        myContributors+=", ";
                    }
                }
                contributors.setText(myContributors);
            }

            @Override
            public void onFailure(Call<List<Contributors>> call, Throwable t) {
                Log.v("REPO CONTRIBUTORS","No contributors");
            }
        });

    }

}
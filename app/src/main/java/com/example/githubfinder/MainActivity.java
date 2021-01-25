package com.example.githubfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText username;
    Button login;
  //  TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username= findViewById(R.id.etUsername);
        login= findViewById(R.id.login);
        //error= findViewById(R.id.error);
        SharedPreferences sharedPreferences= getSharedPreferences("User",MODE_PRIVATE);
        boolean hasLoggedIn= sharedPreferences.getBoolean("isLoggedIn",false);
        if(hasLoggedIn){
            Intent intent= new Intent(MainActivity.this,UserActivity.class);
            String myusername= sharedPreferences.getString("user","Null");
            System.out.println("username :"+myusername);
            intent.putExtra("username",myusername);
            startActivity(intent);
        }
        login.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if(username.getText().toString().length()==0){
                      //  error.setText("Please enter Your Github Username");
                    }
                    else if(username.getText().toString().length()>0){
                       // error.setText("");
                        SharedPreferences sharedPreferences= getSharedPreferences("User",MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("user",username.getText().toString()).apply();
                        editor.putBoolean("isLoggedIn",true).apply();
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        intent.putExtra("username",username.getText().toString());
                        startActivity(intent);
                    }
                }
        });

    }
}
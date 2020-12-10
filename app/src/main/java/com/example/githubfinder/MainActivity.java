package com.example.githubfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText username;
    Button login;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username= findViewById(R.id.etUsername);
        login= findViewById(R.id.login);
        error= findViewById(R.id.error);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().length()==0){
                    error.setText("Please enter Your Github Username");
                }
                else{
                    error.setText("");
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    intent.putExtra("username",username.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
package com.example.githubfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
public class SplashActivity extends AppCompatActivity {

    private ImageView ivSplash;
    private TextView tvSplash;
    private Animation animation;

    private ConstraintLayout clSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //prevent app from system dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();

        ivSplash = findViewById(R.id.ivSplash);
        tvSplash = findViewById(R.id.tvSplash);

        clSplash = findViewById(R.id.clSplash);
        animation  = AnimationUtils.loadAnimation(this, R.anim.splash_animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        ivSplash.startAnimation(animation);
        tvSplash.startAnimation(animation);
    }
}

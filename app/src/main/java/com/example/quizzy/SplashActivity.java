package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.Timer;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ManageSession current_user = new ManageSession(SplashActivity.this);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            public void run() {

                if(!current_user.getUsername().equals(""))
                {
                    Intent intent = new Intent(SplashActivity.this,UserProfile.class);
                    intent.putExtra("username",current_user.getUsername());
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 4000);

    }
}

package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ChooseCategory extends AppCompatActivity {

    RadioButton btn_maths, btn_science, btn_tech, btn_movies;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        btn_maths = findViewById(R.id.btn_maths);
        btn_movies = findViewById(R.id.btn_movies);
        btn_science = findViewById(R.id.btn_science);
        btn_tech = findViewById(R.id.btn_tech);

        //getting username
        Intent userProfile = getIntent();
        username = userProfile.getStringExtra(username);


        btn_maths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent liveQuiz = new Intent();
                liveQuiz.putExtra("username", username);
                liveQuiz.putExtra("category", "Maths");
                startActivity(liveQuiz);
                finish();

            }
        });

        btn_science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent liveQuiz = new Intent();
                liveQuiz.putExtra("username", username);
                liveQuiz.putExtra("category", "Science");
                startActivity(liveQuiz);
                finish();

            }
        });

        btn_tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent liveQuiz = new Intent();
                liveQuiz.putExtra("username", username);
                liveQuiz.putExtra("category", "Technology");
                startActivity(liveQuiz);
                finish();

            }
        });

        btn_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent liveQuiz = new Intent();
                liveQuiz.putExtra("username", username);
                liveQuiz.putExtra("category", "Movies");
                startActivity(liveQuiz);
                finish();

            }
        });

    }
}

package com.example.quizzy;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

    private TextView txtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setTitle("User Profile");

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        txtView = findViewById(R.id.usergreeting);
        txtView.setText("Hello " + username);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent chooseCategory = new Intent(UserProfile.this, ChooseCategory.class);
                chooseCategory.putExtra("username", username);
                startActivity(chooseCategory);
            }
        });



       ;

    }

}

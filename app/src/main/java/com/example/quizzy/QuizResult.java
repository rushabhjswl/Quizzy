package com.example.quizzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizResult extends AppCompatActivity {
    private String currScore, username;
    private TextView txtCurrScore;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    Button btnRanks, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        Intent intent = getIntent();
        currScore = intent.getStringExtra("currScore");
        username = intent.getStringExtra("username");

        //shwoing score on UI
        txtCurrScore = findViewById(R.id.currScore);
        txtCurrScore.setText(currScore + " / 10");


        //adding button listeners

        btnProfile = findViewById(R.id.goToProfile);
        btnProfile.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                finish();

            }
        });



        //saving score to database
        ref = database.getReference("Scores/");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(username).exists()){
                    int childCount = (int)  dataSnapshot.child(username).getChildrenCount();
                    //score added to users scores
                    ref.child(username).child(String.valueOf(childCount+1)).setValue(currScore);
                }
                else{
                    ref.child(username).child("1").setValue(currScore);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

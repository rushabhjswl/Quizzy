package com.example.quizzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class RanksActivity extends AppCompatActivity {

    private ArrayList<UserPercents> arrPercents = new ArrayList<>();
    private String username;
    private ListView ranksListView;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Scores");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranks);

        //getting username
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    String userFromDB = snap.getKey();
                    //Log.e("User From DB", userFromDB);

                    int userQuizCount = (int) dataSnapshot.child(userFromDB).getChildrenCount();
                    int totalUserScore =0;
                    for(int i=1; i<=userQuizCount; i++){

                        String score = dataSnapshot.child(userFromDB).child(i+"").getValue(String.class);
                        //Log.e("Score" +i+ " of "+userFromDB, score+"");
                        totalUserScore = totalUserScore+ Integer.parseInt(score);

                    }


                    //Log.e("total User Score", totalUserScore+"");
                    //Log.e("total User Quizzes", userQuizCount+"");
                    float userPercentage = (totalUserScore/userQuizCount);
                    //Log.e("Percent of "+userFromDB, userPercentage+"");
                    UserPercents userPercent = new UserPercents(userFromDB, userPercentage);
                    arrPercents.add(userPercent);
                }

                //sorting our percents score
                Collections.sort(arrPercents, Collections.reverseOrder());


                //List Adapter
                RankArrayAdapter adapter = new RankArrayAdapter(getApplicationContext(), R.layout.activity_list_text, arrPercents);
                ranksListView = findViewById(R.id.ranksList);
                ranksListView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

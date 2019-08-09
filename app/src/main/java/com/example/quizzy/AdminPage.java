package com.example.quizzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminPage extends AppCompatActivity {

    EditText question,option1,option2,option3,option4,correct_answer;
    Spinner category;
    Button submit;
    AddQuestion add;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Questions");
    long id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        question =(EditText) findViewById(R.id.question);
        option1 =(EditText) findViewById(R.id.option1);
        option2 =(EditText) findViewById(R.id.option2);
        option3 =(EditText) findViewById(R.id.option3);
        option4 =(EditText) findViewById(R.id.option4);
        correct_answer =(EditText) findViewById(R.id.correct_answer);
        category =(Spinner) findViewById(R.id.category);
        submit =(Button) findViewById(R.id.submit);
        add = new AddQuestion();
       // reference = database.getInstance().getReference().child("Questions");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valid())
                {
                    add();
                    reference.child(add.category).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.getChildrenCount() != 0) {
                                id = (dataSnapshot.getChildrenCount());
                                Log.i("Hello", "id = " + id);
                                reference.child(add.category).child(String.valueOf(id + 1)).setValue(add);
                                Toast.makeText(getApplicationContext(), "data inserted successfully", Toast.LENGTH_LONG).show();
                            } else {
                                reference.child(add.category).child(String.valueOf(id + 1)).setValue(add);
                                Toast.makeText(getApplicationContext(), "New data inserted successfully", Toast.LENGTH_LONG).show();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                    id = 0;
                }
            }

        });

    }

    public void add()
    {
        add.setQuestion(question.getText().toString());
        add.setOption1(option1.getText().toString());
        add.setOption2(option2.getText().toString());
        add.setOption3(option3.getText().toString());
        add.setOption4(option4.getText().toString());
        add.setCorrect_answer(correct_answer.getText().toString());
        add.setCategory(category.getSelectedItem().toString());
    }


    //validation for checking empty fields
    public boolean valid()
    {
        boolean valid = true;
        if(question.getText().toString().equals("") || option1.getText().toString().equals("") || option2.getText().toString().equals("") || option3.getText().toString().equals("")|| option4.getText().toString().equals("")|| correct_answer.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_LONG).show();
            valid=false;
        }
        return valid;
    }
}

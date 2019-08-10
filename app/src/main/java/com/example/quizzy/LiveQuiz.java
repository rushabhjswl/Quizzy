package com.example.quizzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LiveQuiz extends AppCompatActivity implements View.OnClickListener{

    private String username, category;
    private int currScore = 0;
    private Button btnNext, btnSubmit;
    int randomQuestions[], questionCount;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    private RadioGroup optionRadioGroup;
    private RadioButton option1, option2, option3, option4, selectedAnswerRadio;
    private TextView txtQuestion, txtQuestionNo;
    private String correctAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_quiz);

        //method call to initiate all variables needed in the activity
        initAllVars();

        fetchQuestion();





    }

    //custom method to initiate all values needed throughout the activity
    public void initAllVars(){

        Intent chooseCategory = getIntent();
        username = chooseCategory.getStringExtra("username");
        category = chooseCategory.getStringExtra("category");

        //later on I will write code to generate random numbers for this array
        randomQuestions = new int[]{1,2,3,4,5,6,7,8,9,10};

        currScore = 0;
        questionCount = 1;

        btnNext = findViewById(R.id.quizBtnNext);
        btnNext.setOnClickListener(this);

        btnSubmit = findViewById(R.id.quizBtnSubmit);
        btnSubmit.setVisibility(View.INVISIBLE);

        optionRadioGroup = findViewById(R.id.radioGroupOptions);



        ref = database.getReference("Questions/"+category);

    }

    //this is function to call in ondatachange. this is becoz ondatachange is faster than ui initialization
    public void initUIElementsToUpdate(){

        option1 = findViewById(R.id.quizOption1);
        option2 = findViewById(R.id.quizOption2);
        option3 = findViewById(R.id.quizOption3);
        option4 = findViewById(R.id.quizOption4);
        txtQuestionNo = findViewById(R.id.quizQuestionNo);
        txtQuestion = findViewById(R.id.quizTxtQuestion);

    }


    public void fetchQuestion(){


            //for first question
           ref.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                   Question question = dataSnapshot.child(""+questionCount).getValue(Question.class);

                       initUIElementsToUpdate();
                   //setting values UI elements
                   txtQuestionNo.setText(questionCount+". ");
                   txtQuestion.setText(question.getQuestion());
                   option1.setText(question.getOption1());
                   option2.setText(question.getOption2());
                   option3.setText(question.getOption3());
                   option4.setText(question.getOption4());

                   //getting correct answer from database
                   correctAnswer = question.getCorrect_answer();
                   //incrementing question count
                   questionCount++;


               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {

               }
           });

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.quizBtnNext :{

                //fetching the question and incrementing score below
                if(optionRadioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please Select an Answer", Toast.LENGTH_SHORT).show();
                }
                //incrementing score on correct answer and fetching new question
                else{
                    int selectedAnswerId = optionRadioGroup.getCheckedRadioButtonId();
                    selectedAnswerRadio = findViewById(selectedAnswerId);

                    //incrementing currScore on correct answer
                    if(selectedAnswerRadio.getText().equals(correctAnswer)){
                        currScore++;
                    }

                    fetchQuestion();

                }
                //checking if its the last question
                if(questionCount == 10){
                    btnNext.setOnClickListener(null);
                    btnNext.setVisibility(View.INVISIBLE);
                    btnSubmit.setVisibility(View.VISIBLE);
                    btnSubmit.setOnClickListener(this);
                }
                break;
            }


            case R.id.quizBtnSubmit: {

                int selectedAnswerId = optionRadioGroup.getCheckedRadioButtonId();
                selectedAnswerRadio = findViewById(selectedAnswerId);
                //incrementing currScore on correct answer
                if(selectedAnswerRadio.getText().equals(correctAnswer)){
                    currScore++;
                }

                Intent quizResult = new Intent(LiveQuiz.this, QuizResult.class);
                quizResult.putExtra("username", username);
                quizResult.putExtra("currScore", currScore+"");
                startActivity(quizResult);

            }





        }

    }
}

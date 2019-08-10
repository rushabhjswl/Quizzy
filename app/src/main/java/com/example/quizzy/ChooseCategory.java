package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseCategory extends AppCompatActivity {

    private RadioButton radioCategoryButton;
    private RadioGroup radioCategoryGroup;
    private Button btnGo;
    private String username;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        //getting username
        Intent userProfile = getIntent();
        username = userProfile.getStringExtra("username");


        addListenerOnRadioGroup();

    }

    //adding listener on radio buttons which will choose category
    public void addListenerOnRadioGroup(){

        radioCategoryGroup = findViewById(R.id.radioCategoryGroup);
        btnGo = findViewById(R.id.btnCategoryGo);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //no category selected
                if(radioCategoryGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please Select a Quiz Category", Toast.LENGTH_SHORT).show();
                }
                //a category is selected, so start the quiz
                else{
                    int selectedID = radioCategoryGroup.getCheckedRadioButtonId();
                    radioCategoryButton = findViewById(selectedID);
                    String choosenCategory = (String) radioCategoryButton.getText();

                    //now we will start quiz with the choosenCategory questions
                    Intent intent = new Intent(ChooseCategory.this, LiveQuiz.class);
                    intent.putExtra("category", choosenCategory);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }



            }
        });

    }

}

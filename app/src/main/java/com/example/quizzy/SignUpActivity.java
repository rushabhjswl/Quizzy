package com.example.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    String username, password, email, reTypePassword;
    EditText edt;
    Button signup;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Users");
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = (Button) findViewById(R.id.signup_signup_button);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               setAllStrings();
                if(areEntriesValid() == true){


                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(!dataSnapshot.child(username).exists()){
                                //registering user
                                user = new User(username,password, email);
                                reference.child(user.getUsername()).setValue(user);

                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }




            }
        });

    }


    //set variables to the edittext entries made by user
    public void setAllStrings(){

        edt = findViewById(R.id.signup_username);
        username = edt.getText().toString();

        edt = findViewById(R.id.signup_password);
        password = edt.getText().toString();

        edt = findViewById(R.id.signup_password2);
        reTypePassword = edt.getText().toString();

        edt = findViewById(R.id.signup_email);
        email = edt.getText().toString();

    }


    //check is user entries are valid
    public boolean areEntriesValid(){

        //if any field is empty, return false
        if(username.equals("") || password.equals("") || reTypePassword.equals("") || email.equals("")){
            Toast.makeText(getApplicationContext(), "Please Fill all Fields", Toast.LENGTH_SHORT).show();
            return false;
        }


        //passwords not same
        else if(!password.equals(reTypePassword)){
            Toast.makeText(getApplicationContext(), "Enter same password", Toast.LENGTH_SHORT).show();
            return false;
        }

        //email format
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            Toast.makeText(getApplicationContext(), "Enter Correct Email", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }




}


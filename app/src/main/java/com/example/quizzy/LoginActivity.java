package com.example.quizzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    Button login, signup;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Users");
    String username, password;
    EditText edt;
    private String EXTRA_MESSAGE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = (Button) findViewById(R.id.login_signup_button);
        login = (Button) findViewById(R.id.login_login_button);

        //on clicking signup button, we start the SignUpActivity
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


        //logging in
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                setAllStrings();

                if(areEntriesValid() == true){
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.child(username).exists()){


                                User user = dataSnapshot.child(username).getValue(User.class);
                                String usertype = user.username;


                                if(usertype.equals("admin") && username.equals(user.username) && password.equals(user.password)){
                                    Log.i("In ADMIN Login", "Success");
                                    Intent intent = new Intent(LoginActivity.this, AdminPage.class);
                                    intent.putExtra(EXTRA_MESSAGE, username);
                                    startActivity(intent);
                                }
                                //we start the UserProfile Activity
                                else {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, UserProfile.class);
                                    intent.putExtra("username", username);
                                    startActivity(intent);
                                }



                            }
                            else{
                                Toast.makeText(getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
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

    public void setAllStrings(){

        edt = findViewById(R.id.login_username);
        username = edt.getText().toString();

        edt = findViewById(R.id.login_password);
        password = edt.getText().toString();


    }

    public boolean areEntriesValid() {

        //if any field is empty, return false
        if (username.equals("") || password.equals("")) {
            Toast.makeText(getApplicationContext(), "Please Fill all Fields", Toast.LENGTH_SHORT).show();
            return false;
        }else
            return true;
    }


}

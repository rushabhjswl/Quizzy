package com.example.quizzy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText re_typepass;
    EditText email;
    Button signup;
    FirebaseDatabase database;
    DatabaseReference reference;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.signup_username);
        password = (EditText) findViewById(R.id.signup_password);
        re_typepass = (EditText) findViewById(R.id.signup_password2);
        email = (EditText) findViewById(R.id.signup_email);
        signup = (Button) findViewById(R.id.signup_button2);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Users");
        user = new User();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

        public void registerUser()
        {
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());
            user.setPassword2(re_typepass.getText().toString());
            user.setEmail(email.getText().toString());
            reference.child(user.getUsername()).setValue(user);
        }

}


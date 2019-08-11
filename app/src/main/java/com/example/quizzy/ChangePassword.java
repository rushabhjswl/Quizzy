package com.example.quizzy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePassword extends AppCompatActivity {

    EditText change_pass;
    Button change;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        change_pass =(EditText) findViewById(R.id.change_pass);
        change =(Button) findViewById(R.id.change);
        Intent intent1 = getIntent();
        final String user = intent1.getStringExtra("user");

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String new_password = change_pass.getText().toString();
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(new_password.equals(dataSnapshot.child(user).child("password").getValue())){

                            Toast.makeText(getApplicationContext(),"Password cannot be same as old password",Toast.LENGTH_LONG).show();

                        }
                        else{
                            reference.child(user).child("password").setValue(new_password);
                            Toast.makeText(getApplicationContext(),"password changed successfully",Toast.LENGTH_LONG).show();
                            Intent transfer = new Intent(ChangePassword.this,LoginActivity.class);
                            transfer.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP );
                            startActivity(transfer);
                            finish();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }
        });
    }
}

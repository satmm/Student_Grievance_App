package com.example.studentgrievieance.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.studentgrievieance.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    TextView t1;
    TextInputEditText t2,t3,t4;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String uid;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        t1=findViewById(R.id.name);
        t2=findViewById(R.id.email);
        t3=findViewById(R.id.phone);
        t4=findViewById(R.id.date);
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("students");
        uid=user.getUid();
        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // Retrieve the student details
                            String name = snapshot.child("name").getValue(String.class);
                            String email = snapshot.child("Department").getValue(String.class);
                            String phone = snapshot.child("USN").getValue(String.class);
                            String date = snapshot.child("phone").getValue(String.class);
                            // ... retrieve other details as needed

                            // Update the UI with the retrieved student details
                            // ... get references to other UI elements

                            t1.setText(name);
                            t2.setText(email);
                            t3.setText(phone);
                            t4.setText(date);
                            // ... update other UI elements
                        }
                    }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
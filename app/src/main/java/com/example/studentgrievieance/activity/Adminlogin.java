package com.example.studentgrievieance.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentgrievieance.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Adminlogin extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText email;
    private EditText password;
    private TextView signuprt,f;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String uid;
    private Button login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        auth=FirebaseAuth.getInstance();
        email=findViewById(R.id.et_email);
        password=findViewById(R.id.et_password);
        login=findViewById(R.id.btn_login);
        signuprt=findViewById(R.id.tv_registerButton);
        f=findViewById(R.id.tv_forgotPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference usersRef = database.getReference("Admin");
                usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot adminSnapshot : dataSnapshot.getChildren()) {
                            // Retrieve the email and password from the snapshot
                            String email = adminSnapshot.child("email").getValue(String.class);
                            String password = adminSnapshot.child("password").getValue(String.class);
                            // Compare the retrieved data with user input
                            if (email != null && password != null && email.equals(user) && password.equals(pass)) {
                                if (!user.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                                    if (!pass.isEmpty()) {
                                        auth.signInWithEmailAndPassword(user, pass)
                                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                    @Override
                                                    public void onSuccess(AuthResult authResult) {
                                                        Toast.makeText(Adminlogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(Adminlogin.this, AdminDashboard.class));
                                                        finish();


                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(Adminlogin.this, "Login failed", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                }
                            }
                        }

                                // Login successful, proceed to the next activity or perform necessary actions
                        //Toast.makeText(Adminlogin.this, "Login failed", Toast.LENGTH_SHORT).show();

                            }



                            // Proceed to the next activity or perform necessary actions


                                // Login failed, show an error message or take appropriate action




                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle any errors
                    }
                });



            }
            });
                signuprt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Adminlogin.this, AdminSignup.class));
                    }
                });
                f.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Adminlogin.this,OtpValidation.class));
                    }
                });







    }
}
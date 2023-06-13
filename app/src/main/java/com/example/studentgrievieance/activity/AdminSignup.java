package com.example.studentgrievieance.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentgrievieance.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminSignup extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Admin");
    private FirebaseAuth auth;
    private EditText username,name,phone,db,id;
    private EditText pass,conpass;
    private Button loginButton;
    private TextView loginrt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        auth=FirebaseAuth.getInstance();
        conpass=findViewById(R.id.confirmpassword);
        username=findViewById(R.id.email);
        pass=findViewById((R.id.password));
        loginButton=findViewById((R.id.btn_register));
        phone=findViewById(R.id.phone);
        id=findViewById(R.id.id);
        loginrt=findViewById(R.id.tv_loginButton);
        name=findViewById(R.id.adminusername);
        db=findViewById(R.id.collegename);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String password=pass.getText().toString();
                String n=name.getText().toString();
                String d=db.getText().toString();
                String p=phone.getText().toString();
                String i=id.getText().toString();
                String c=conpass.getText().toString();
                if(n.isEmpty()) {
                    name.setError("name cannot be empty");
                }
                else if(user.isEmpty())
                {
                    username.setError("email cannot be empty");
                }
                else if(p.isEmpty())
                {
                    phone.setError("phone number cannot be empty");
                }
                if (!isCollegeIdValid(i)) {
                    id.setError("Invalid college ID format");
                    return;
                }
                else if(d.isEmpty())
                {
                    db.setError("Date of birth cannot be empty");
                }
                else if(password.isEmpty())
                {
                    pass.setError("password cannot be empty");
                }
                else if(c.isEmpty())
                {
                    conpass.setError("phone number cannot be empty");
                }
                else if (!password.equals(c))
                {
                    Toast.makeText(AdminSignup.this,"Passwords are not matching",Toast.LENGTH_SHORT).show();
                }
                else
                {


                    auth.createUserWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                databaseReference.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
                                    String currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        if(snapshot.hasChild(p))
                                        {
                                            Toast.makeText(AdminSignup.this,"Phone number is already registered",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            FirebaseUser u=auth.getCurrentUser();
                                            HashMap<String,String> usermap=new HashMap<>();
                                            usermap.put("name",n);
                                            usermap.put("email",user);
                                            usermap.put("date of birth",d);
                                            usermap.put("phone number",p);
                                            usermap.put("College id",i);
                                            usermap.put("password",password);

                                            databaseReference.child(currentUserUID).setValue(usermap);
                                            Toast.makeText(AdminSignup.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                                            finish();

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                                Toast.makeText(AdminSignup.this,"Signup succesfull",Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(AdminSignup.this,Adminlogin.class));
                            }
                            else
                            {
                                Toast.makeText(AdminSignup.this,"SignUp failed",Toast.LENGTH_SHORT).show();
                                Toast.makeText(AdminSignup.this,"login successful" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                }

            }
        });
        loginrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminSignup.this,Adminlogin.class));
                finish();
            }
        });


    }
    private boolean isCollegeIdValid(String collegeId) {
        // Add your college ID verification conditions here
        // Example: Verify if it starts with "COLLEGE-" and has a specific length
        return collegeId.startsWith("COLLEGE-") && collegeId.length() == 12;
    }
}


package com.example.studentgrievieance.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentgrievieance.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class postComplaint extends AppCompatActivity {
    TextInputEditText  e1, e2, e3, e4, e5,e7;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Complaint");
    TextInputEditText e6;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_complaint);
        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.usn);
        e3 = findViewById(R.id.date);
        e4 = findViewById(R.id.department);
        e5 = findViewById(R.id.category);
        e6 = findViewById(R.id.description);
        e7=findViewById(R.id.college_name);
        b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = e1.getText().toString();
                String usn = e2.getText().toString();
                String d = e3.getText().toString();
                String dpt = e4.getText().toString();
                String c = e5.getText().toString();
                String desc = e6.getText().toString();
                String cn=e7.getText().toString();
                if (user.isEmpty()) {
                    e1.setError("name cannot be empty");
                } else if (usn.isEmpty()) {
                    e2.setError("usn cannot be empty");
                } else if (d.isEmpty()) {
                    e3.setError("date cannot be empty");
                } else if (dpt.isEmpty()) {
                    e4.setError("Department cannot be empty");
                } else if (c.isEmpty()) {
                    e5.setError("category cannot be empty");
                } else if (desc.isEmpty()) {
                    e6.setError("Description cannot be empty");
                }
                else if (cn.isEmpty()) {
                    e7.setError("Description cannot be empty");
                }
                else {
                    databaseReference.child("Complaint").addListenerForSingleValueEvent(new ValueEventListener() {
                        String currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            HashMap<String, String> usermap = new HashMap<>();
                            usermap.put("name", user);
                            usermap.put("USN", usn);
                            usermap.put("date", d);
                            usermap.put("Department", dpt);
                            usermap.put("category", c);
                            usermap.put("description", desc);
                            usermap.put("College Name",cn);

                            databaseReference.child(user).setValue(usermap);
                            Toast.makeText(postComplaint.this, "Complaint registered successfully", Toast.LENGTH_SHORT).show();
                            finish();

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
}

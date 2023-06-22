package com.example.studentgrievieance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentgrievieance.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddStudentActivity extends AppCompatActivity {
    private EditText nameEditText, usnEditText, phoneEditText, departmentEditText, semEditText;
    private Button saveButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        nameEditText = findViewById(R.id.name);
        usnEditText = findViewById(R.id.usn);
        phoneEditText = findViewById(R.id.phone);
        departmentEditText = findViewById(R.id.department);
        semEditText = findViewById(R.id.sem);
        saveButton = findViewById(R.id.button);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String usn = usnEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String department = departmentEditText.getText().toString().trim();
                String semester = semEditText.getText().toString().trim();
                if (name.isEmpty()) {
                    nameEditText.setError("name cannot be empty");
                } else if (usn.isEmpty()) {
                    usnEditText.setError("usn cannot be empty");
                } else if (phone.isEmpty()) {
                    phoneEditText.setError("phone cannot be empty");
                } else if (department.isEmpty()) {
                    departmentEditText.setError("Department cannot be empty");
                } else if (semester.isEmpty()) {
                    semEditText.setError("semester cannot be empty");
                }
                else {
                    databaseReference.child("students").addListenerForSingleValueEvent(new ValueEventListener() {
                        String currentUserUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            HashMap<String, String> usermap = new HashMap<>();
                            usermap.put("name", name);
                            usermap.put("USN", usn);
                            usermap.put("phone",phone);
                            usermap.put("Department",department);
                            usermap.put("category", semester);

                            databaseReference.child(usn).setValue(usermap);
                            Toast.makeText(AddStudentActivity.this, "Student data saved successfully", Toast.LENGTH_SHORT).show();
                            // Clear the input fields
                            nameEditText.setText("");
                            usnEditText.setText("");
                            phoneEditText.setText("");
                            departmentEditText.setText("");
                            semEditText.setText("");
                            // Display a toast message
                           // Toast.makeText(AddStudentActivity.this, "Details saved successfully", Toast.LENGTH_SHORT).show();

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

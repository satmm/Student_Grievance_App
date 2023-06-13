package com.example.studentgrievieance;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.studentgrievieance.activity.Complaint;

public class ViewGrievances extends AppCompatActivity {
    TextView textViewComplaint,textViewDate,textViewDepartment,textViewUsn,textViewName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grievances);
        textViewName = findViewById(R.id.t1);
        textViewDepartment = findViewById(R.id.t2);
//        textViewUsn = findViewById(R.id.t3);
//        textViewCategory = findViewById(R.id.t4);
//        textViewDescription = findViewById(R.id.t5);
//        textViewDate = findViewById(R.id.t6);

        // Retrieve the complaint object from the intent
        Complaint complaint = getIntent().getParcelableExtra("complaint");

        if (complaint != null) {
            // Set the student details in the TextViews
            textViewName.setText("Name: " + complaint.getName());
            textViewDepartment.setText("Department: " + complaint.getDepartment());
            textViewUsn.setText("USN: " + complaint.getUsn());
//            textViewCategory.setText("Complaint Category: " + complaint.getCategory());
//            textViewDescription.setText("Complaint Description: " + complaint.getDescription());
//            textViewDate.setText("Date of Registration: " + complaint.getDate());
        }
    }
            // Set the content in other TextViews
        }


        // Set the content in the TextView
//        textViewName.setText(value);
////        textViewUsn.setText(v2);
//
//        // Set the content in the TextView
//        textViewDepartment.setText(v4);
//        textViewComplaint.setText(v3);

        // Set the content in the TextView
//        textViewDate.setText(v5);



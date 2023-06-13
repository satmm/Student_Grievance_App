package com.example.studentgrievieance;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentgrievieance.activity.Complaint;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentGrievanceActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        // Initialize the ListView and ArrayList
        listView = findViewById(R.id.listView3);
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        // Retrieve data from Firebase Realtime Database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Complaint");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear the previous data
                dataList.clear();

                // Iterate through all the child nodes
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Retrieve the value of each child node


                    Complaint complaint = snapshot.getValue(Complaint.class);
                    if (complaint != null && complaint.getName() != null && complaint.getUsn() != null  ) {
                        String value = "Name:"+complaint.getName();
                        dataList.add(value);
                        String v2="USN:"+ complaint.getUsn();
                        String v3="Complaint Category:"+complaint.getCategory();
                        String v4="Department:"+complaint.getDepartment();
                        String v5="Date:"+complaint.getDate();
                        String v6="Complaint Description:"+complaint.getDescription();
                        getIntent().putExtra("value",value);
                        getIntent().putExtra("Department",v4);
                        getIntent().putExtra("USN",v2);
                        getIntent().putExtra("Complaint category",v3);
                        getIntent().putExtra("Complaint Description",v6);
                        getIntent().putExtra("Date of Registration",v5);


                    }
                }

                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error case
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = dataList.get(position);

                // Retrieve the Complaint object from the selected position
                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Complaint").child(name);
                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Clear the previous data
                        dataList.clear();

                        // Iterate through all the child nodes
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            // Retrieve the value of each child node


                            Complaint complaint = snapshot.getValue(Complaint.class);
                            if (complaint != null && complaint.getName() != null && complaint.getUsn() != null) {
                                String value = "Name:" + complaint.getName();
                                // String v2="USN:"+ complaint.getUsn();
                                //String v3="Complaint Category:"+complaint.getCategory();
                                String v4 = "Department:" + complaint.getDepartment();
//                                String v5="Date:"+complaint.getDate();
//                                String v6="Complaint Description:"+complaint.getDescription();
                                getIntent().putExtra("value", value);
                                getIntent().putExtra("Department", v4);
//                                getIntent().putExtra("USN",v2);
//                                getIntent().putExtra("Complaint category",v3);
//                                getIntent().putExtra("Complaint Description",v6);
//                                getIntent().putExtra("Date of Registration",v5);


                            }
                        }

                        // Notify the adapter that the data has changed
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle error case
                    }
                });
            }
        });

        }
    }


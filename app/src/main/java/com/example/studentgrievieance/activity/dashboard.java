package com.example.studentgrievieance.activity;



import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.studentgrievieance.R;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import androidx.appcompat.widget.Toolbar;

public class dashboard extends AppCompatActivity  {
    DrawerLayout drawerLayout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String uid;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    TextView tex1,tex2;
   Button b1;
   ImageView i;
    private CardView c1, c2, c3, c4, c5, c6;
    ImageView i1;
    private Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        c1=findViewById(R.id.card1);
        c2=findViewById(R.id.card2);
        c3=findViewById(R.id.card3);
        c4=findViewById(R.id.card4);
        c5=findViewById(R.id.card5);
        c6=findViewById(R.id.card6);
        i=findViewById(R.id.home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Handle navigation item clicks here
                int itemId = menuItem.getItemId();

                // Example navigation logic
                if (itemId == R.id.i1) {
                    // Handle item 1 click
                    startActivity(new Intent(dashboard.this, Profile.class));
                    // Start a new activity, fragment, or perform any desired action
                } else if (itemId == R.id.i2) {
                    // Handle item 2 click
                } else if (itemId == R.id.i5) {
                    startActivity(new Intent(dashboard.this,MainActivity.class));
                    finish();
                    // Handle item 3 click
                }

                drawerLayout.closeDrawers();
                return true;
            }
        });
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        reference= FirebaseDatabase.getInstance().getReference("user");
//        uid=user.getUid();
//        reference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    // Retrieve the student details
//                    String name = snapshot.child("name").getValue(String.class);
//                    String email = snapshot.child("email").getValue(String.class);
//
//                    // ... retrieve other details as needed
//
//                    // Update the UI with the retrieved student details
//                    // ... get references to other UI elements
//
//                    tex1.setText(name);
//                    tex2.setText(email);
//
//                    // ... update other UI elements
//                }
//            }
//
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,Description.class);
                String title="Academic Issues";
                String content="This category includes grievances related to\n" +
                        "1.Course content\n" +
                        "2.Grading\n" +
                        "3.Evaluations\n" +
                        "4.Academic policies\n" +
                        "5.Unfair treatment by faculty members or instructors";
                intent.putExtra("content", content);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,Description.class);
                String title="Administration and Financial Matters";
                String content="This category includes grievances related to administrative processes such as\n" +
                        "1.Registration\n" +
                        "2.Scheduling\n" +
                        "3.Fees payment\n" +
                        "4.Financial aid\n" +
                        "5.Scholorship\n" +
                        "6.Issues with university department or offices";
                intent.putExtra("content", content);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,Description.class);
                String title="Facilities and Infrastructure";
                String content="This category includes grievance;s related to\n" +
                        "1.Manitenance issues\n" +
                        "2.Safety concerns\n" +
                        "3.Access to resources\n" +
                        "4.Campus infrastructure\n" +
                        "5.Inadquate facilities";
                intent.putExtra("content", content);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,Description.class);
                String title="Student Services";
                String content="This category covers grievances associated with student support services such as\n" +
                        "1.Counselling\n" +
                        "2.Health services\n" +
                        "3.Carreer guidence\n" +
                        "4.Hostel management\n" +
                        "5.Food court\n" +
                        "6.Transportation and Library";
                intent.putExtra("content", content);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,Description.class);
                String title="Campus Climate";
                String content="This category includes grievances involving\n" +
                        "1.Campus environment\n" +
                        "2.Community dynamics\n" +
                        "3.Inclusivity\n" +
                        "4.Diversity\n" +
                        "5.Campus culture";
                intent.putExtra("content", content);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(dashboard.this,Description.class);
                String title="Descrimination and Harrassement";
                String content="This category includes grievances related to discrimination or harassement based on factors such as\n" +
                        "1.Race discrimination\n" +
                        "2.Gender discrimination\n" +
                        "3.Religion discrimination\n" +
                        "4.Disability discrimination\n" +
                        "5.Ragging";
                intent.putExtra("content", content);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



//private void loadFragment(AFragment aFragment) {
        //FragmentManager fragmentManager = getSupportFragmentManager();
        ////FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.add(R.id.container, aFragment);
        //fragmentTransaction.commit();
    //}

   // public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Uncheck the previously active menu item
        //if (activeMenuItem != null) {
            //activeMenuItem.setChecked(false);
        //}


        // Set the selected menu item as active
        //menuItem.setChecked(true);
        //activeMenuItem = menuItem;
        //int id = menuItem.getItemId();

        //if (id == R.id.i2) {
            //Intent intent = new Intent(this, feedback.class);
            //startActivity(intent);
            //return true;
        //}

        //return super.onOptionsItemSelected(menuItem);


    //}




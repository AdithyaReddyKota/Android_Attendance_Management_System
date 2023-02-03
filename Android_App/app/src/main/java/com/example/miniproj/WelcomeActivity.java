package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button admin,staff,student;
    DBmanager DBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        admin=(Button) findViewById(R.id.btnadmin);
        staff=(Button) findViewById(R.id.btnstaff);
        student=(Button) findViewById(R.id.btnstudent);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // DBmanager.del_sub1();
                Intent intent= new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(intent);
            }
        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),StaffActivity.class);
                startActivity(intent);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),StudentviewActivity.class);
                startActivity(intent);
            }
        });
    }
}
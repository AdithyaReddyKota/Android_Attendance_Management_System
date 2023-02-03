package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminviewActivity extends AppCompatActivity {
    Button addstudent,delstudent,addstaff,delstaff,viewattend,exit,addsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview);

        addstudent=(Button) findViewById(R.id.btnaddstudent);
        delstudent=(Button) findViewById(R.id.btndelstudent);
        addstaff=(Button) findViewById(R.id.btnaddstaff);
        delstaff=(Button) findViewById(R.id.btndelstaff);
        viewattend=(Button) findViewById(R.id.viewattendance);
        addsub=(Button) findViewById(R.id.addsub);
        exit=(Button) findViewById(R.id.btnexit);


        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddstudentActivity.class);
                startActivity(intent);
            }
        });

        delstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DeletestudentActivity.class);
                startActivity(intent);
            }
        });

        addstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StaffregisterActivity.class);
                startActivity(intent);
            }
        });

        delstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DeletestaffActivity.class);
                startActivity(intent);
            }
        });

        addsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Addsubject.class);
                startActivity(intent);
            }
        });

        viewattend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RedirectAttendance.class);
                startActivity(intent);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentviewActivity extends AppCompatActivity {
    Button viewatt,tot,exit,cls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentview);


        viewatt =  (Button) findViewById(R.id.btnview);
        tot =  (Button) findViewById(R.id.btntot);
        exit =  (Button) findViewById(R.id.btnexit);
        cls =  (Button) findViewById(R.id.btnclass);


        viewatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StudentPortal.class);
                startActivity(intent);
            }
        });

        tot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),IndividualAttendance.class);
                startActivity(intent);
            }
        });

        cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TotalAttendance.class);
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
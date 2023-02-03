package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StaffviewActivity extends AppCompatActivity {
    Button markAtt,viewatt,tot,exit,btnind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffview);

        markAtt =  (Button) findViewById(R.id.btnmark);
        viewatt =  (Button) findViewById(R.id.btnview);
        tot =  (Button) findViewById(R.id.btntot);
        exit =  (Button) findViewById(R.id.btnexit);
        btnind =  (Button) findViewById(R.id.btnind);





        markAtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Markattendance.class);
                startActivity(intent);
            }
        });

        viewatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ViewClassAttendeance.class);
                startActivity(intent);
            }
        });

        tot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TotalAttendance.class);
                startActivity(intent);
            }
        });

        btnind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),IndividualAttendance.class);
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
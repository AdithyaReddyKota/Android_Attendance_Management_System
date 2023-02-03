package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IndividualAttendance extends AppCompatActivity {
    private EditText getRegET;
    private Button getAttendenceButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_attendance);

        getRegET = findViewById(R.id.getRegno);
        getAttendenceButton = findViewById(R.id.getAttendenceButton);


        getAttendenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String regNo = getRegET.getText().toString();



                Intent in = new Intent(IndividualAttendance.this,SubWiseAttendance.class);
                in.putExtra("regNo",regNo);
                startActivity(in);
            }
        });
    }
}
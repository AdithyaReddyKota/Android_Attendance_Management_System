package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TotalAttendance extends AppCompatActivity {
    private DBmanager dbManager;
    private SimpleCursorAdapter simpleCursorAdapter;
    Spinner coursespinner,semspinner,secspinner;
    String sem,course,sec;
    private String[] course_names = new String[]{"CSE", "ECE", "MECH", "IT", "EEE"};
    private String[] section_names = new String[]{"A","B","C"};
    private String[] sem_names = new String[]{"I-1", "I-2", "II-1", "II-2", "III-1", "III-2", "IV-1", "IV-2"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_attendance);

        coursespinner = (Spinner) findViewById(R.id.Class_Cource_Spinner);
        semspinner = (Spinner) findViewById(R.id.semspinner);
        secspinner = (Spinner) findViewById(R.id.secspinner);



        ////////////////////////////
        coursespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                course = (String) coursespinner.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_course = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, course_names);
        adapter_course
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursespinner.setAdapter(adapter_course);
        /////////////////////////

        semspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                sem = (String) semspinner.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_sem = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, sem_names);
        adapter_sem
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semspinner.setAdapter(adapter_sem);
        /////////////////////////


        secspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                sec= (String) secspinner.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_sec = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, section_names);
        adapter_sec
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secspinner.setAdapter(adapter_sec);
        /////////////////////////
        Button getDetails = findViewById(R.id.ClassAttendenceButton);

        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String course = coursespinner.getSelectedItem().toString();
                String sem = semspinner.getSelectedItem().toString();

                String sec = secspinner.getSelectedItem().toString();



                Intent intent = new Intent(TotalAttendance.this,ViewTotalAttendancePercentage.class);
                intent.putExtra("course",course);
                intent.putExtra("semester",sem);
                intent.putExtra("sec",sec);
                startActivity(intent);

            }
        });

    }

}
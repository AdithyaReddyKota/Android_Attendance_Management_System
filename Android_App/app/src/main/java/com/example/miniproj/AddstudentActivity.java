package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddstudentActivity extends AppCompatActivity {

    private EditText name, regno; //
    String  course, sem,sec;
    private DBmanager dbManager;
    private Button add_student;
    Spinner coursespinner,semspinner,secspinner;
    private String[] course_names = new String[] {"CSE","ECE","MECH","IT","EEE"};
    private String[] section_names = new String[] {"A","B","C"};
    private String[] sem_names = new String[]{"I-1", "I-2", "II-1", "II-2", "III-1", "III-2", "IV-1", "IV-2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);

        add_student = findViewById(R.id.btnadd);

        coursespinner=(Spinner)findViewById(R.id.Coursespinner);
        semspinner=(Spinner)findViewById(R.id.semspinner);
        secspinner=(Spinner)findViewById(R.id.secspinner);


        ////////////////////////////
        coursespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                course =(String) coursespinner.getSelectedItem();

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
                sem =(String) semspinner.getSelectedItem();

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

        /////////////////////////

        secspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
                sec =(String) secspinner.getSelectedItem();

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


        name = (EditText)findViewById(R.id.name);
        regno = (EditText)findViewById(R.id.roll);

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        final String Insert_name   = name.getText().toString();
                        final String Insert_regno  = regno.getText().toString();
                        final String Insert_cource = coursespinner.getSelectedItem().toString();
                        final String Insert_sem    = semspinner.getSelectedItem().toString();
                        final String Insert_sec    = secspinner.getSelectedItem().toString();

                try {
                            dbManager = new DBmanager(AddstudentActivity.this);
                            dbManager.open();
                            dbManager.insert(Insert_name, Insert_regno, Insert_cource, Insert_sem, Insert_sec);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(AddstudentActivity.this, ViewStudent.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
            }
        });
    }
}
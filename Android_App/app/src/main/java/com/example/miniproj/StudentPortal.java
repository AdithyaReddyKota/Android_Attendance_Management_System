package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class StudentPortal extends AppCompatActivity {

    private DBmanager dbManager;
    private EditText getRegET;
    Spinner coursespinner,semspinner;
    String sem,course;
    private Button getAttendenceButton;
    private String[] course_names = new String[]{"CSE", "ECE", "MECH", "IT", "EEE"};
    private String[] sem_names = new String[]{"I-1", "I-2", "II-1", "II-2", "III-1", "III-2", "IV-1", "IV-2"};

    private SimpleCursorAdapter simpleCursorAdapter;

    private String[] from = new String[]{DBhelper.SUB_NAME};
    private int [] to = new int[]{android.R.id.text1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_portal);



        getRegET = findViewById(R.id.getRegno);
        getAttendenceButton = findViewById(R.id.getAttendenceButton);

        coursespinner = (Spinner) findViewById(R.id.Cource_Spinner);
        semspinner = (Spinner) findViewById(R.id.semester_spinner);

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



        dbManager = new DBmanager(this);
        dbManager.open();
        Cursor cursor = dbManager.getSubjects();


        //TODO: Subject Spinner
        final  Spinner sub_spinner = findViewById(R.id.subject_spinner);
        simpleCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,cursor,from,to,0);
        simpleCursorAdapter.notifyDataSetChanged();
        simpleCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sub_spinner.setAdapter(simpleCursorAdapter);

        getAttendenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String regNo = getRegET.getText().toString();
                String cource = coursespinner.getSelectedItem().toString();
                String sem =  semspinner.getSelectedItem().toString();

                //get Subjects from database
                Cursor cursor1 = (Cursor)sub_spinner.getSelectedItem();
                @SuppressLint("Range") String subject = cursor1.getString(cursor1.getColumnIndex(DBhelper.SUB_NAME));

                Intent in = new Intent(StudentPortal.this,ViewAttendenceByStudentList.class);
                in.putExtra("regno",regNo);
                in.putExtra("course",cource);
                in.putExtra("sem",sem);
                in.putExtra("subject",subject);

                startActivity(in);
            }
        });
    }


}

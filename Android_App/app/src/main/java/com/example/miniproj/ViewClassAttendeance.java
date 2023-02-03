package com.example.miniproj;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ViewClassAttendeance extends AppCompatActivity {

    private DBmanager dbManager;
    private SimpleCursorAdapter simpleCursorAdapter;
    Spinner coursespinner,semspinner;
    String sem,course;
    private EditText date;
    private DatePickerDialog datePickerDialog;
    private String[] course_names = new String[]{"CSE", "ECE", "MECH", "IT", "EEE"};
    private String[] view_options = new String[]{"View by student","Class Wise Attandance Details"};

    private String[] sem_names = new String[]{"I-1", "I-2", "II-1", "II-2", "III-1", "III-2", "IV-1", "IV-2"};

    private String[] from = new String[]{DBhelper.SUB_NAME};
    private int[] to = new int[]{android.R.id.text1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_class_attendence);

        coursespinner = (Spinner) findViewById(R.id.Class_Cource_Spinner);
        semspinner = (Spinner) findViewById(R.id.semspinner);


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




        //Subject Spinner

        dbManager = new DBmanager(this);
        dbManager.open();
        Cursor cursor = dbManager.getSubjects();


        //TODO: Subject Spinner
        final Spinner sub_spinner = findViewById(R.id.Class_subject_spinner);
        simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, from, to, 0);
        simpleCursorAdapter.notifyDataSetChanged();
        simpleCursorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sub_spinner.setAdapter(simpleCursorAdapter);




        //Attendence View Selector
        final Spinner aws = findViewById(R.id.SelectAttendenceViewSpinner2);

        ArrayAdapter<String> adapter_adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, view_options);
        adapter_adp
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aws.setAdapter(adapter_adp);


        Button spin = findViewById(R.id.spinnerButton2);

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classwise = aws.getSelectedItem().toString();
                if (classwise.equals("Class Wise Attandance Details")) {
                    Intent in = new Intent(ViewClassAttendeance.this, ViewAttendenceByClassList.class);
                    startActivity(in);
                } else {
                    Intent in = new Intent(ViewClassAttendeance.this, StudentPortal.class);
                    startActivity(in);
                }

            }
        });


        // Date Picker

        date = findViewById(R.id.Classdatepick);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mday = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(ViewClassAttendeance.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int MonthofYear, int DayofMonth) {
                                date.setText(DayofMonth + "/" + (MonthofYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth,mday);
                datePickerDialog.show();
            }
        });


        Button getDetails = findViewById(R.id.ClassAttendenceButton);

        getDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String course = coursespinner.getSelectedItem().toString();
                String sem = semspinner.getSelectedItem().toString();

                Cursor cursor1 = (Cursor)sub_spinner.getSelectedItem();
                @SuppressLint("Range") String subject = cursor1.getString(cursor1.getColumnIndex(DBhelper.SUB_NAME));



                final String Date = date.getText().toString();

                Intent intent = new Intent(ViewClassAttendeance.this,ViewAttendenceByClassList.class);
                intent.putExtra("course",course);
                intent.putExtra("semester",sem);
                intent.putExtra("sub",subject);
                intent.putExtra("Date",Date);
                startActivity(intent);

            }
        });
    }
}




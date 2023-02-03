package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Markattendance extends AppCompatActivity {

    private DBmanager dbManager;
    private SimpleCursorAdapter adapter;
    Spinner coursespinner,semspinner,secspinner;
    String sem,course,sec;
    private EditText date;
    private DatePickerDialog datePickerDialog;
    private String[] course_names = new String[]{"CSE", "ECE", "MECH", "IT", "EEE"};
    private String[] section_names = new String[] {"A","B","C"};

    private String[] sem_names = new String[]{"I-1", "I-2", "II-1", "II-2", "III-1", "III-2", "IV-1", "IV-2"};
    String[] from = new String[]{DBhelper.SUB_NAME};
    int[] to = new int[]{android.R.id.text1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markattendance);
        //Date Picker Code
        date = findViewById(R.id.datepick);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(Markattendance.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int MonthOfYear, int DayOfMonth) {
                                date.setText(DayOfMonth + "/" + (MonthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        coursespinner = (Spinner) findViewById(R.id.Coursespinner);
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


        //Subject Spinner
        final Spinner subject_Spinner = findViewById(R.id.subjectspinner);

        //open connection to database and fetching data
        dbManager = new DBmanager(this);
        dbManager.open();
        Cursor cursor = dbManager.teacher_attendence_subjects();

        // SimpleCursorAdapter for a SUBJECT spinner
        adapter = new SimpleCursorAdapter(Markattendance.this, android.R.layout.simple_spinner_item, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        subject_Spinner.setAdapter(adapter);




        Button test = findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor cursor1 = (Cursor) subject_Spinner.getSelectedItem();
                @SuppressLint("Range") String sub = cursor1.getString(cursor1.getColumnIndex(DBhelper.SUB_NAME));


                //selected cource
                String course = coursespinner.getSelectedItem().toString();

                //selected semester
                String sem = semspinner.getSelectedItem().toString();
                String sec = secspinner.getSelectedItem().toString();


                final String Date = date.getText().toString();

                Intent intent = new Intent(Markattendance.this,Attendancelist.class);
                intent.putExtra("course",course);
                intent.putExtra("semester",sem);
                intent.putExtra("sub",sub);
                intent.putExtra("section",sec);
                intent.putExtra("Date",Date);
                startActivity(intent);

            }
        });

    }



}

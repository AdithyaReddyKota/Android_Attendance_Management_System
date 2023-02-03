package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ViewAttendenceByStudentList extends AppCompatActivity {

    private DBmanager dbManager;
    private SimpleCursorAdapter adapter;
    private ListView listView;

    private String[] from = new String[]{DBhelper.AT_STUDENT_NAME,DBhelper.AT_STUDENT_REGNO
            ,DBhelper.AT_STUDENT_CLASS,DBhelper.AT_STUDENT_SEM,DBhelper.AT_SUBJECT,DBhelper.AT_DATE,
            DBhelper.AT_STATUS};

    private int[] to = new int[]{R.id.Student_attendence_details_name,R.id.Student_attendence_details_regno,R.id.Student_attendence_details_cource,
            R.id.Student_attendence_details_sem,R.id.Student_attendence_details_subject,R.id.Student_attendence_details_date,
            R.id.Student_attendence_details_status};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendence_by_student_list);


        Intent in = getIntent();
        String regno=in.getStringExtra("regno");
        String course =in.getStringExtra("course");
        String sem =in.getStringExtra("sem");
        String subject =in.getStringExtra("subject");


        dbManager = new DBmanager(this);
        dbManager.open();
        Cursor cursor = dbManager.View_attendence_by_student(regno,course,sem,subject);

        listView = findViewById(R.id.students_attenence_details_listview);
        listView.setEmptyView(findViewById(R.id.empty_attendence_details));

        adapter = new SimpleCursorAdapter(ViewAttendenceByStudentList.this,R.layout.students_attendence_details_row,
                cursor,from,to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

    }

}

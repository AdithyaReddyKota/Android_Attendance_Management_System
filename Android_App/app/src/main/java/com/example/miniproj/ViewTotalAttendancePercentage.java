package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class ViewTotalAttendancePercentage extends AppCompatActivity {
    private DBmanager dbManager;
    private SimpleCursorAdapter adapter;
    private ListView listView;

    private String[] from = new String[]{DBhelper.TAT_ID
            ,DBhelper.TAT_ATTENDED,DBhelper.TAT_HELD,DBhelper.TAT_PERCENT};

    private int[] to = new int[]{R.id.Student_attendence_details_regno,R.id.Student_classes_attended,
            R.id.Student_classes_held,R.id.Student_attendence_percent};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_total_attendance_percentage);

        Intent in = getIntent();

        String course =in.getStringExtra("course");
        String sem =in.getStringExtra("semester");
        String sec =in.getStringExtra("sec");



        //try {
            dbManager = new DBmanager(this);
            dbManager.open();

            Cursor cursor1=dbManager.get_roll_nums(course,sem,sec);
            ArrayList<Float> aList = new ArrayList<Float>() ;
            ArrayList<Float> hList = new ArrayList<Float>() ;
            ArrayList<Float> pList = new ArrayList<Float>() ;
            Float a,b,c;
            Float k;


            Cursor cursor2;
            for (int i=0;i<cursor1.getCount();i++){
                cursor2=dbManager.get_attended(cursor1.getString(0));
                a= Float.valueOf((cursor2.getCount()));
                cursor2=dbManager.get_held(cursor1.getString(0));
                b= Float.valueOf((cursor2.getCount()));
                k= (a / b ) *100;
                pList.add(k);
                dbManager.insert_into_tatattendence(cursor1.getString(0),a,b,k);
                cursor1.moveToNext();
            }
            Cursor cursor = dbManager.tat();
            dbManager.del();


            listView = findViewById(R.id.class_students_attenence_details_listview);
            listView.setEmptyView(findViewById(R.id.class_empty_attendence_details));

            adapter = new SimpleCursorAdapter(ViewTotalAttendancePercentage.this,R.layout.student_total_attendance,
                    cursor,from,to,0);
            adapter.notifyDataSetChanged();

            listView.setAdapter(adapter);
      //  }catch (Exception e){
      //      e.printStackTrace();
      //  }
    }
}
package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class SubWiseAttendance extends AppCompatActivity {
    private DBmanager dbManager;
    private SimpleCursorAdapter adapter;
    private ListView listView;

    private String[] from = new String[]{DBhelper.TAT_SUBJECT
            ,DBhelper.TAT_ATTENDED_SUBJECT,DBhelper.TAT_HELD_SUBJECT,DBhelper.TAT_PERCENT_SUBJECT};

    private int[] to = new int[]{R.id.Student_subject,R.id.Student_classes_attended,
            R.id.Student_classes_held,R.id.Student_attendence_percent};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_wise_attendance);


        Intent in = getIntent();

        String regNo =in.getStringExtra("regNo");

        String c,sem,sec;
        //try {
        dbManager = new DBmanager(this);
        dbManager.open();

        Cursor cursor1=dbManager.get_year_sem_sec(regNo);
        c=cursor1.getString(0);
        sem=cursor1.getString(1);
        sec=cursor1.getString(2);
        Cursor cursor3 = dbManager.get_subjects_year(c,sem,sec);
        ArrayList<Float> aList = new ArrayList<Float>() ;
        ArrayList<Float> hList = new ArrayList<Float>() ;
        ArrayList<Float> pList = new ArrayList<Float>() ;
        Float a,b;
        Float k;


        Cursor cursor2;
        for (int i=0;i<cursor3.getCount();i++){
            cursor2=dbManager.get_attended_subject(regNo,cursor3.getString(0));
            a= Float.valueOf((cursor2.getCount()));
            cursor2=dbManager.get_held_subject(regNo,cursor3.getString(0));
            b= Float.valueOf((cursor2.getCount()));
            k= (a / b ) *100;
            pList.add(k);
            dbManager.insert_into_subwise(cursor3.getString(0),a,b,k);
            cursor3.moveToNext();
        }
        Cursor cursor = dbManager.tat_sub();
        dbManager.del_sub();


        listView = findViewById(R.id.class_students_attenence_details_listview);
        listView.setEmptyView(findViewById(R.id.class_empty_attendence_details));

        adapter = new SimpleCursorAdapter(SubWiseAttendance.this,R.layout.ind_sub_align,
                cursor,from,to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        //  }catch (Exception e){
        //      e.printStackTrace();
        //  }
    }
}
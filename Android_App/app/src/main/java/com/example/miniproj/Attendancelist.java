package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Attendancelist extends AppCompatActivity {

    private ListView listView;
    private SimpleCursorAdapter adapter;
    private DBmanager dbManager;
    Button submit,reset;


    final String[] from = new String[]{DBhelper.S_NAME,DBhelper.S_REGNO,DBhelper.S_COURSE};

    final int[] to = new int[]{R.id.student_attendence_name,R.id.student_attendence_regNo,R.id.student_attendence_Cource};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendancelist);



        dbManager = new DBmanager(this);
        dbManager.open();

        Intent in = getIntent();
        String course   = in.getStringExtra("course");
        String semester = in.getStringExtra("semester");
        String section = in.getStringExtra("section");



        Cursor cursor  = dbManager.get_Attendence_List(course,semester,section);

        listView = findViewById(R.id.attendenceList);
        listView.setEmptyView(findViewById(R.id.emptyAttendenceSheet));

        adapter = new SimpleCursorAdapter(Attendancelist.this,R.layout.attendence__row,cursor,from,to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);


         submit =(Button) findViewById(R.id.Submit_attendence);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String subject = intent.getStringExtra("sub");
                String date = intent.getStringExtra("Date");
                String semester = intent.getStringExtra("semester");


                for (int i = 0; i < listView.getCount(); i++) {

                    View v = getViewByPosition(i, listView);

                    RadioGroup radioGroup = v.findViewById(R.id.radio_group);

                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radio_stat = (RadioButton) findViewById(selectedId);

                    String stat = radio_stat.getText().toString();

                    TextView t = v.findViewById(R.id.student_attendence_name);
                    String name = t.getText().toString();

                    TextView t1 = v.findViewById(R.id.student_attendence_regNo);
                    String reg = t1.getText().toString();

                    TextView t2 = v.findViewById(R.id.student_attendence_Cource);
                    String cour = t2.getText().toString();

                    long success = dbManager.insert_into_attendence(name, cour, subject, semester, reg, stat, date);
                    if (success > 0) {
                        submit.setVisibility(View.GONE);

                        new AlertDialog.Builder(Attendancelist.this)
                                .setTitle("Success")
                                .setMessage("Attendence Successfully Taken")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent in = new Intent(Attendancelist.this, Markattendance.class);
                                        in.setFlags(in.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(in);
                                        Attendancelist.this.finish();
                                    }
                                }).show();

                    }
                }


            }
        });


        reset=(Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<listView.getCount(); i++){

                    View v = getViewByPosition(i,listView);

                    RadioGroup radioGroup = v.findViewById(R.id.radio_group);
                    radioGroup.clearCheck();
                }
            }
        });


    }

    public View getViewByPosition(int position, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition ) {
            return listView.getAdapter().getView(position, listView.getChildAt(position), listView);
        } else {
            final int childIndex = position - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }


}

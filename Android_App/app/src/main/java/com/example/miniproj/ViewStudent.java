package com.example.miniproj;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewStudent extends AppCompatActivity {

    private ListView list;
    private DBmanager dbManager;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{DBhelper.S_ID, DBhelper.S_NAME, DBhelper.S_REGNO,
            DBhelper.S_COURSE, DBhelper.S_SEMESTER};
    final int[] to = new int[]{R.id.id, R.id._name, R.id._regno, R.id._cource, R.id._sem};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_student);


        dbManager = new DBmanager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        list = findViewById(R.id.Student_ListView);
        list.setEmptyView(findViewById(R.id.empty));


        adapter = new SimpleCursorAdapter(this, R.layout.view_list_record, cursor, from, to,0 );
        adapter.notifyDataSetChanged();

        list.setAdapter(adapter);

        //TODO: On click listener for item selected.

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long ViewId) {

                TextView idtextview     = view.findViewById(R.id.id);
                TextView nametextview   = view.findViewById(R.id._name);
                TextView regtextview    = view.findViewById(R.id._regno);
                TextView courcetextview = view.findViewById(R.id._cource);
                TextView semtextview    = view.findViewById(R.id._sem);

                String id = idtextview.getText().toString();
                String name = nametextview.getText().toString();
                String regno = regtextview.getText().toString();
                String cource = courcetextview.getText().toString();
                String sem = semtextview.getText().toString();

                Intent modify_intent = new Intent(ViewStudent.this, AddstudentActivity.class);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("regno", regno);
                modify_intent.putExtra("cource", cource);
                modify_intent.putExtra("sem", sem);

                startActivity(modify_intent);


            }
        });
    }
}
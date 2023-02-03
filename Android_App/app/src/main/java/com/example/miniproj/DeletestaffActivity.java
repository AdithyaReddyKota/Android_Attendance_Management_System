package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeletestaffActivity extends AppCompatActivity {

    EditText reg;
    Button del;
    DBmanager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletestaff);

        reg = (EditText) findViewById(R.id.getRegno);
        del = (Button) findViewById(R.id.delstaff);
        dbManager = new DBmanager(this);
        dbManager.open();

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reg1 =(reg.getText().toString());
                dbManager.delete_teacher(reg1);
                new AlertDialog.Builder(DeletestaffActivity.this)
                        .setTitle("Success")
                        .setMessage("Staff Deleted Successfully")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                                Intent intent = new Intent(DeletestaffActivity.this, AdminviewActivity.class)
                                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        }).show();
            }
        });
    }

}
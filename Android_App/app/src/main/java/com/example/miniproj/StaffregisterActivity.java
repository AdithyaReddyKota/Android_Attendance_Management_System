package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StaffregisterActivity extends AppCompatActivity {

    EditText username, password, repassword,email,name;
    Button signup, signin;
    DBmanager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffregister);

        name = (EditText) findViewById(R.id.sname);
        email = (EditText) findViewById(R.id.semail);
        username = (EditText) findViewById(R.id.susername);
        password = (EditText) findViewById(R.id.spassword);
        repassword = (EditText) findViewById(R.id.srepassword);
        signup = (Button) findViewById(R.id.sbtnsignup);
        signin = (Button) findViewById(R.id.sbtnsignin);
        dbManager = new DBmanager(this);
        dbManager.open();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String teacher_name = name.getText().toString();
                final String teacher_email = email.getText().toString();
                final String teacher_userId = username.getText().toString();
                final String teacher_password = password.getText().toString();
                final String repass = repassword.getText().toString();

                if (teacher_name.equals("") ||teacher_email.equals("") ||teacher_userId.equals("") || teacher_password.equals("") || repass.equals("")) {
                    Toast.makeText(StaffregisterActivity.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (repass.equals("12345")) {
                        Boolean checkuser = dbManager.checkusername(teacher_userId);
                        if (checkuser == false) {
                            switch (view.getId()){
                                case R.id.sbtnsignup:

                                    try {


                                        dbManager.insert_into_teacher(teacher_name, teacher_email, 123, teacher_userId, teacher_password);

                                        new AlertDialog.Builder(StaffregisterActivity.this)
                                                .setTitle("Success")
                                                .setMessage(" Registered Successfully")
                                                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {


                                                        Intent intent = new Intent(StaffregisterActivity.this, StaffActivity.class)
                                                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        startActivity(intent);

                                                    }
                                                }).show();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }

                                    break;
                            }
                        } else {
                            Toast.makeText(StaffregisterActivity.this, "User Already Exists! Please Sign in..", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(StaffregisterActivity.this, "Check Retyped Password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
                startActivity(intent);

            }
        });
    }
}

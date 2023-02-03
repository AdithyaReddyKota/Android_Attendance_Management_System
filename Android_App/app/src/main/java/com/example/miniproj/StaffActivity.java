package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StaffActivity extends AppCompatActivity {
    EditText username,password;
    Button btnlogin , btnregister;
    private DBmanager dbManager;
    private final int REQUEST_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        username=(EditText) findViewById(R.id.username1);
        password=(EditText) findViewById(R.id.password1);
        btnlogin=(Button) findViewById(R.id.btnsignin1);
        btnregister=(Button) findViewById(R.id.btnregister);
        dbManager = new DBmanager(this);
        dbManager.open();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(StaffActivity.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        if (dbManager.Teacher_login(user, pass)) {
                            Toast.makeText(StaffActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), StaffviewActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(StaffActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                    }
                }
            }});

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StaffregisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
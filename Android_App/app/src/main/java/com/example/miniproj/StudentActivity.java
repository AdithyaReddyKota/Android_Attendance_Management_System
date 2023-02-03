package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin ;
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.btnsignin);

        DB = new DBhelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(StudentActivity.this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checckuser = true;//DB.checkusername(user);
                    if(checckuser == true){
                        Boolean checkpass =true;// DB.checkusernamepassword(user,pass);
                        if(checkpass == true){
                            Toast.makeText(StudentActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),StudentviewActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(StudentActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(StudentActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    EditText username,password;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        btnlogin=(Button) findViewById(R.id.btnsignin);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("") || pass.equals("") ){
                    Toast.makeText(AdminActivity.this , "Please Enter all Fields",Toast.LENGTH_SHORT).show();
                }
                else{

                    if(user.equals("admin") && pass.equals("12345")){
                        Toast.makeText(AdminActivity.this , "Sign in Successful",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),AdminviewActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(AdminActivity.this , "Invalid Credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
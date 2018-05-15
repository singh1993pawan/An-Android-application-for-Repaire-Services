package com.example.p1s.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ResetPassword extends AppCompatActivity {

    EditText current_password,new_password,conf_password;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        current_password = (EditText) findViewById(R.id.currentpassword);
        new_password = (EditText) findViewById(R.id.newpassword);
        conf_password = (EditText) findViewById(R.id.confirmpassword);
        userid=getIntent().getExtras().getString("start");

//.makeText(this,userid,Toast.LENGTH_SHORT).show();
        TextView t=(TextView)findViewById(R.id.text_1);
        t.setText(userid);
    }


    public void ResetPassword(View view){

            String Cpassword = current_password.getText().toString();
            String Npassword = new_password.getText().toString();
            String CNpassword = conf_password.getText().toString();
            String type = "reset";

            BackgroundReset backgroundReset = new BackgroundReset(this);
            backgroundReset.execute(type, Cpassword, Npassword, CNpassword, userid);


    }



}

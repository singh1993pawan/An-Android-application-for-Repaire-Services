package com.example.p1s.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class select_option extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option);
    }
    public void foruser(View view){
        Intent startNewActivity = new Intent(this, Main3Activity.class);
        startActivity(startNewActivity);
    }
    public void serviceprovider(View view){
        Intent startNewActivity = new Intent(this, sp_option.class);
        startActivity(startNewActivity);
    }
}

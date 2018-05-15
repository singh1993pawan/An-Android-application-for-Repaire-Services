package com.example.p1s.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class USearch extends AppCompatActivity {

    EditText mix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usearch);
        mix = (EditText) findViewById(R.id.search);
    }

    public void Search(View view)
    {
        String s = mix.getText().toString();
        Intent i = new Intent(this,UShow.class);
        i.putExtra("key",s);
        startActivity(i);
    }
}

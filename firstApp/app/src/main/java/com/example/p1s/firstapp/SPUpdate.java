package com.example.p1s.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SPUpdate extends AppCompatActivity {

    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spupdate);


        user=getIntent().getExtras().getString("start");
        TextView t=(TextView)findViewById(R.id.text_1);
        t.setText(user);
    }



    public void UpdateEmail(View view)
    {
        Intent intent = new Intent(this,SP_Email.class);
        intent.putExtra("start",user);
        startActivity(intent);
    }

    public void UpdateAddress(View view)
    {
        Intent intent = new Intent(this,SP_Address.class);
        intent.putExtra("start",user);
        startActivity(intent);

    }
    public void UpdateContact(View view)
    {
        Intent intent = new Intent(this,SP_Contact.class);
        intent.putExtra("start",user);
        startActivity(intent);
    }
}

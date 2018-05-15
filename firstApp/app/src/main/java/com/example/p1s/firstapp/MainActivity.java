package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mShowDialog = (Button) findViewById(R.id.button3);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.dialog_regis,null);
            Button b1 = (Button) mView.findViewById(R.id.user);
            Button b2 = (Button) mView.findViewById(R.id.sp);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(intent);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,sp_option.class);
                    startActivity(intent);
                }
            });
            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();
        }
    });


}

  /* public void Registration(View view)
    {
        Intent intent = new Intent(this,select_option.class);
        startActivity(intent);
    }
    */

    public void Login(View view)
    {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }


}

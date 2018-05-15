package com.example.p1s.firstapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class selectCategory extends AppCompatActivity {


    EditText mix;
    Spinner sp;
    String dmh="",user;
    ArrayAdapter<CharSequence> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        user=getIntent().getExtras().getString("start");
        mix = (EditText) findViewById(R.id.search);
        sp = (Spinner) findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this,R.array.category,android.R.layout
                .simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp.setAdapter(adapter);
       // sp.setPrompt("Select your Category");

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" selected",
                //       Toast.LENGTH_LONG).show();
                dmh=parent.getItemAtPosition(position).toString();
                if(dmh.equalsIgnoreCase("Please Select Category"))
                {
                    AlertDialog.Builder mbuilder=new AlertDialog.Builder(selectCategory.this);
                    mbuilder.setMessage("Please Select Category");
                    mbuilder.setTitle("Category Alert");
                    mbuilder.setNegativeButton("DISMISS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        }
                    }).create().show();
                }
                else
                {
                    Intent i=new Intent(selectCategory.this, Show.class);
                    i.putExtra("key", dmh);
                    i.putExtra("start", user);
                    startActivity(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void selectCategorySpinner(View view)
    {

    }

    public void Search(View view)
    {
        String s = mix.getText().toString();
        Intent i = new Intent(this,SearchShow.class);
        i.putExtra("key",s);
        i.putExtra("start",user);
        startActivity(i);
    }

    public void onBackPressed()
    {
        Intent i = new Intent(this,After_login.class);
        i.putExtra("start",user);
        this.startActivity(i);
    }
}

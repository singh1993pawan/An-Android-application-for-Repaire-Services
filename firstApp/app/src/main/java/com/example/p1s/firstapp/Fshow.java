package com.example.p1s.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.p1s.firstapp.mMySQL.FDownloader;

public class Fshow extends AppCompatActivity {

    String urlAddress="http://andromeda.nitc.ac.in/~m140383ca/ShowFeedback.php";
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fshow);

        type=getIntent().getStringExtra("key");
        //Toast.makeText(this,type,Toast.LENGTH_LONG).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        new FDownloader(Fshow.this,urlAddress,rv,type).execute();
        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }
}

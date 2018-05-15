package com.example.p1s.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.p1s.firstapp.mMySQL.UDownloader;

public class UShow extends AppCompatActivity {


    String urlAddress="http://andromeda.nitc.ac.in/~m140383ca/Ufish-search.php";
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ushow);
        type=getIntent().getStringExtra("key");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        new UDownloader(UShow.this,urlAddress,rv,type).execute();
       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }
}

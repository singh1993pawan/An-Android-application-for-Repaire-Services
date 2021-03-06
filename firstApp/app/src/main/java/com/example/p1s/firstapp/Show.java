package com.example.p1s.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.p1s.firstapp.mMySQL.Downloader;

public class Show extends AppCompatActivity {

    String urlAddress="http://andromeda.nitc.ac.in/~m140383ca/ShowDetail.php";
    String type,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //Fetching search
        type=getIntent().getStringExtra("key");
        user=getIntent().getExtras().getString("start");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        new Downloader(Show.this,urlAddress,rv,type,user).execute();
       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }
}

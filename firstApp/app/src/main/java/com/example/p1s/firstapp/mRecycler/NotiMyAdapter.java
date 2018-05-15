package com.example.p1s.firstapp.mRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.p1s.firstapp.BackgroundDelete;
import com.example.p1s.firstapp.R;
import com.example.p1s.firstapp.mDataObject.Spacecraft;

import java.util.ArrayList;

/**
 * Created by P1 S on 5/4/2017.
 */

public class NotiMyAdapter extends RecyclerView.Adapter<NotiMyHolder> {

    Context c;
    ArrayList<Spacecraft> spacecrafts;
    String user;
    public NotiMyAdapter(Context c, ArrayList<Spacecraft> spacecrafts,String user) {
        this.c = c;
        this.spacecrafts = spacecrafts;

        this.user=user;
    }

    @Override
    public NotiMyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model2,parent,false);
        return new NotiMyHolder(v);
    }

    @Override
    public void onBindViewHolder(NotiMyHolder holder, int position) {

        holder.nameTxt.setText("Rated By : " + spacecrafts.get(position).getName());
        Log.d("Pawan:",spacecrafts.get(position).getName());





        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

            }
        });


        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str="delete";
                BackgroundDelete backgroundDelete = new BackgroundDelete(NotiMyAdapter.this);
                backgroundDelete.execute(str,user);
            }
        });

    }





    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }






}


package com.example.p1s.firstapp.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p1s.firstapp.R;
import com.example.p1s.firstapp.mDataObject.Spacecraft;

import java.util.ArrayList;

/**
 * Created by P1 S on 4/29/2017.
 */

public class FMyAdapter extends RecyclerView.Adapter<FMyHolder> {


    Context c;
    boolean checked;
    TextView textView;
    String category;
    ArrayList<Spacecraft> spacecrafts;
    String type;
    public FMyAdapter(Context c, ArrayList<Spacecraft> spacecrafts,String type) {
        this.c = c;
        this.spacecrafts = spacecrafts;
        this.type=type;

    }

    @Override
    public FMyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.fmodel,parent,false);
        return new FMyHolder(v);
    }




    @Override
    public void onBindViewHolder(final FMyHolder holder, int position) {

        holder.nameTxt.setText("NAME :" + spacecrafts.get(position).getName());
        holder.address.setText("ADDRESS :" + spacecrafts.get(position).getAddress());
        holder.contact.setText("CONTACT :" + spacecrafts.get(position).getContact());
        holder.prize.setText("Rating :" + spacecrafts.get(position).getPrize());

        final String number,email;
        number=spacecrafts.get(position).getContact();


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));

                if(intent.resolveActivity(c.getPackageManager())!=null)  {
                    c.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }






}


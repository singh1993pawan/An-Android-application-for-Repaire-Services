package com.example.p1s.firstapp.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.p1s.firstapp.R;
import com.example.p1s.firstapp.mDataObject.Spacecraft;

import java.util.ArrayList;

/**
 * Created by P1 S on 4/19/2017.
 */

public class UMyAdapter extends RecyclerView.Adapter<UMyHolder> {

        Context context;
        ArrayList<Spacecraft> spacecrafts;
        String type;
public UMyAdapter(Context c, ArrayList<Spacecraft> spacecrafts,String type) {
        this.context = c;
        this.spacecrafts = spacecrafts;
        this.type=type;
        }

@Override
public UMyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model1,parent,false);
        return new UMyHolder(v);
        }


public void onBindViewHolder(UMyHolder holder, int position) {

        holder.nameTxt.setText("NAME :" + spacecrafts.get(position).getName());
        holder.address.setText("ADDRESS :" + spacecrafts.get(position).getAddress());
        holder.contact.setText("CONTACT :" + spacecrafts.get(position).getContact());
       // holder.prize.setText("VISIT CHARGE(Rs) :" + spacecrafts.get(position).getPrize());

        final String number;
        number=spacecrafts.get(position).getContact();



        holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        final Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));

                        if(intent.resolveActivity(context.getPackageManager())!=null)  {
                                context.startActivity(intent);
                        }
                }
        });

}

@Override
public int getItemCount() {
        return spacecrafts.size();
        }


        }



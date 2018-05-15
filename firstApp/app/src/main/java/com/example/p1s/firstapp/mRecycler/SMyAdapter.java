package com.example.p1s.firstapp.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.p1s.firstapp.R;
import com.example.p1s.firstapp.Rating;
import com.example.p1s.firstapp.mDataObject.Spacecraft;

import java.util.ArrayList;

import static com.example.p1s.firstapp.R.id.user;

/**
 * Created by P1 S on 4/14/2017.
 */

public class SMyAdapter extends RecyclerView.Adapter<SMyHolder> {


    Context c;
    ArrayList<Spacecraft> spacecrafts;
    String type;
    public SMyAdapter(Context c, ArrayList<Spacecraft> spacecrafts,String type) {
        this.c = c;
        this.spacecrafts = spacecrafts;
        this.type=type;
    }

    @Override
    public SMyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new SMyHolder(v);
    }


    public void onBindViewHolder(SMyHolder holder, int position) {

        holder.nameTxt.setText("NAME :" + spacecrafts.get(position).getName());
        holder.address.setText("ADDRESS :" + spacecrafts.get(position).getAddress());
        holder.contact.setText("CONTACT :" + spacecrafts.get(position).getContact());
        holder.prize.setText("VISIT CHARGE(Rs) :" + spacecrafts.get(position).getPrize());

        final String number,email;
        number=spacecrafts.get(position).getContact();
        email=spacecrafts.get(position).getEmail();



        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

            }
        });


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));

                if(intent.resolveActivity(c.getPackageManager())!=null)  {
                    c.startActivity(intent);
                }
            }
        });

        holder.bt.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent i = new Intent(c, Rating.class);

                i.putExtra("type",type);
                i.putExtra("email",email);
                i.putExtra("user",user);
                c.startActivity(i);
                Toast.makeText(c,user,Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }


}

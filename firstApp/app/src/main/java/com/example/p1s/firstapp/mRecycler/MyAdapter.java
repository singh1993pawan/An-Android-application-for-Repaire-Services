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
import com.example.p1s.firstapp.Rating;
import com.example.p1s.firstapp.mDataObject.Spacecraft;

import java.util.ArrayList;

/**
 * Created by P1 S on 4/11/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    boolean checked;
    TextView textView;
    String category;
    ArrayList<Spacecraft> spacecrafts;
    String type,user;
    public MyAdapter(Context c, ArrayList<Spacecraft> spacecrafts,String type,String user) {
        this.c = c;
        this.spacecrafts = spacecrafts;
        this.type=type;
        this.user=user;
}

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new MyHolder(v);
    }




    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {

        holder.nameTxt.setText("NAME :" + spacecrafts.get(position).getName());
        holder.address.setText("ADDRESS :" + spacecrafts.get(position).getAddress());
        holder.contact.setText("CONTACT :" + spacecrafts.get(position).getContact());
        holder.prize.setText("VISIT CHARGE(Rs) :" + spacecrafts.get(position).getPrize());
        String avg=spacecrafts.get(position).getAvg();
        if(avg.equals("0"))
        {
            holder.average.setText("Rating(5) :" + "NO RATING");
            holder.number.setText("Person :" + "0");

        }
        else
        {
            holder.average.setText("Rating(5) :" + spacecrafts.get(position).getAvg());
            holder.number.setText("Person :" + spacecrafts.get(position).getPerson());

        }



        final String number,email;
        number=spacecrafts.get(position).getContact();
        email=spacecrafts.get(position).getEmail();
        /*holder.btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+number));
                c.startActivity(intent);
            }
        });*/

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
             // Toast.makeText(c,user,Toast.LENGTH_LONG).show();

          }
      });
    }

            @Override
            public int getItemCount() {
                return spacecrafts.size();
            }






}

 /*AlertDialog.Builder mbuilde=new AlertDialog.Builder(c);
                mbuilde.setTitle("CALL SERVICE PROVIDER");
                mbuilde.setMessage("Do You Really Want To Call ?");
                mbuilde.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+number));
                        c.startActivity(intent);

                        //Toast.makeText(c,"Calling at".concat(number),Toast.LENGTH_SHORT).show();

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
                */

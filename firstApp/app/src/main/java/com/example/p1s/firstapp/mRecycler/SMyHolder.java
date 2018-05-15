package com.example.p1s.firstapp.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.p1s.firstapp.R;

/**
 * Created by P1 S on 4/14/2017.
 */

public class SMyHolder extends RecyclerView.ViewHolder {

    TextView nameTxt,address,contact,prize,textView;
    ImageButton btn;
    Button bt;
    private ItemClickListener itemClickListener;

    public SMyHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        address = (TextView) itemView.findViewById(R.id.nameAddress);
        contact = (TextView) itemView.findViewById(R.id.nameContact);
        prize = (TextView) itemView.findViewById(R.id.namePrize);
       // btn = (ImageButton) itemView.findViewById(R.id.button2);
        //itemView.setOnClickListener(SMyHolder.this);
        //bt = (Button) itemView.findViewById(R.id.rating);
       // itemView.setOnClickListener(this);

    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }
}

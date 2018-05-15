package com.example.p1s.firstapp.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.p1s.firstapp.R;

/**
 * Created by P1 S on 4/11/2017.
 */

public class MyHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

    ItemClickListener itemClickListener;
    TextView nameTxt, address, contact, prize, average,number;
    ImageButton btn;
    Button bt;
    public MyHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        address = (TextView) itemView.findViewById(R.id.nameAddress);
        contact = (TextView) itemView.findViewById(R.id.nameContact);
        prize = (TextView) itemView.findViewById(R.id.namePrize);
        average = (TextView) itemView.findViewById(R.id.average);
        number = (TextView) itemView.findViewById(R.id.number);
        btn = (ImageButton) itemView.findViewById(R.id.button2);
        itemView.setOnClickListener(this);
        bt = (Button) itemView.findViewById(R.id.rating);
        itemView.setOnClickListener(this);


    }


    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(getLayoutPosition());
    }


}

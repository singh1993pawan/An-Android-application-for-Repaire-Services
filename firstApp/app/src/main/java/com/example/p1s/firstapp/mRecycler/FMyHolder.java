package com.example.p1s.firstapp.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.p1s.firstapp.R;

/**
 * Created by P1 S on 4/29/2017.
 */

public class FMyHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

    ItemClickListener itemClickListener;
    TextView nameTxt, address, contact, prize, textView;
    ImageButton btn;
    public FMyHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        address = (TextView) itemView.findViewById(R.id.nameAddress);
        contact = (TextView) itemView.findViewById(R.id.nameContact);
        prize = (TextView) itemView.findViewById(R.id.namePrize);
        btn = (ImageButton) itemView.findViewById(R.id.button2);
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
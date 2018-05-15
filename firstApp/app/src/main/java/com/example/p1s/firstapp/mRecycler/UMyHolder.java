package com.example.p1s.firstapp.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.p1s.firstapp.R;

/**
 * Created by P1 S on 4/19/2017.
 */

public class UMyHolder extends RecyclerView.ViewHolder {


    ImageButton btn;
    TextView nameTxt,address,contact,prize;
    public UMyHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        address = (TextView) itemView.findViewById(R.id.nameAddress);
        contact = (TextView) itemView.findViewById(R.id.nameContact);
        //prize = (TextView) itemView.findViewById(R.id.namePrize);

        btn=(ImageButton)itemView.findViewById(R.id.button2);

    }

}

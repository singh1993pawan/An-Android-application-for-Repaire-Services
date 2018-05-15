package com.example.p1s.firstapp.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.p1s.firstapp.R;

/**
 * Created by P1 S on 5/4/2017.
 */

public class NotiMyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    TextView nameTxt;
    ImageButton del;
    private ItemClickListener itemClickListener;
    public NotiMyHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView) itemView.findViewById(R.id.nameTxt);

    del=(ImageButton) itemView.findViewById(R.id.del);
        itemView.setOnClickListener(this);

    }




    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(getLayoutPosition());
    }


}



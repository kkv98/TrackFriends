package com.kkv.friendtracker.trackfriends;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kkv.friendtracker.trackfriends.R;


public class ListOnlineViewHolder extends RecyclerView.ViewHolder{
    public TextView email;
    ItemClickListener itemClickListener;
    private ListOnlineViewHolder.ItemClickListener mClickListener;

    public ListOnlineViewHolder(View itemView) {
        super(itemView);
        email=itemView.findViewById(R.id.textemail);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());

            }
        });
    }


    public void setOnClickListener(ListOnlineViewHolder.ItemClickListener clickListener){
        mClickListener = clickListener;
    }

    public interface ItemClickListener
    {
      public void onItemClick(View view,int postion);
    }
}

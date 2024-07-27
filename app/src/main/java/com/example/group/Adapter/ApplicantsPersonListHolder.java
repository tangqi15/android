package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;

public class ApplicantsPersonListHolder extends RecyclerView.ViewHolder{
    ImageView img_event_details_item_poster;
    TextView tv_event_details_item_screen;

    TextView tv_event_details_item_time;
    public ApplicantsPersonListHolder(@NonNull View itemView) {
        super(itemView);
        img_event_details_item_poster = (ImageView)itemView.findViewById(R.id.img_event_details_item_poster);
        tv_event_details_item_screen = (TextView)itemView.findViewById(R.id.tv_event_details_item_screen);

        tv_event_details_item_time = (TextView)itemView.findViewById(R.id.tv_event_details_item_time);
    }
}
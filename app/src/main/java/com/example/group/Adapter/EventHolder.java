package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;

public class EventHolder extends RecyclerView.ViewHolder{

    TextView tv_activity_address;
    TextView tv_activity_start_time;
    TextView tv_activity_finish_time;
    ImageView img_activity_pic;
    TextView tv_group_activity_name;
    public EventHolder(@NonNull View itemView) {
        super(itemView);
       /* tv_activity_start_time = itemView.findViewById(R.id.tv_activity_start_time);*/
        tv_activity_finish_time = itemView.findViewById(R.id.tv_activity_finish_time);
        tv_activity_address = itemView.findViewById(R.id.tv_activity_address);
        img_activity_pic = itemView.findViewById(R.id.img_activity_pic);
        tv_group_activity_name = itemView.findViewById(R.id.tv_group_activity_name);
    }
}
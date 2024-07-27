package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;

public class GroupActivityHolder extends RecyclerView.ViewHolder {
    ImageView img_group_activity_poster;
    TextView tv_group_activity_start_time;
    TextView tv_group_activity_finish_time;
    TextView tv_group_activity_address;
    TextView tv_activity_name;
    public GroupActivityHolder(@NonNull View itemView) {
        super(itemView);
        img_group_activity_poster = itemView.findViewById(R.id.img_group_activity_poster);
        tv_group_activity_start_time = itemView.findViewById(R.id.tv_group_activity_start_time);
        tv_group_activity_finish_time = itemView.findViewById(R.id.tv_group_activity_finish_time);
        tv_group_activity_address = itemView.findViewById(R.id.tv_group_activity_address);
        tv_activity_name = itemView.findViewById(R.id.tv_activity_name);
    }

}

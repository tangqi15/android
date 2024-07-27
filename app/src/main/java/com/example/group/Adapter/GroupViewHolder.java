package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;

public class GroupViewHolder extends RecyclerView.ViewHolder {
    ImageView img_group_pic;
    TextView tv_group_name,tv_group_intro;
    TextView tv_group_type;

    public GroupViewHolder(@NonNull View itemView) {
        super(itemView);
        img_group_pic = itemView.findViewById(R.id.img_group_pic);
        tv_group_name = itemView.findViewById(R.id.tv_group_name);
        tv_group_intro = itemView.findViewById(R.id.tv_group_intro);
        tv_group_type = itemView.findViewById(R.id.tv_group_type);
    }
}

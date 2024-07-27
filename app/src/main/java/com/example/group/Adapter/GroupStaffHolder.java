package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;

public class GroupStaffHolder extends RecyclerView.ViewHolder {
    ImageView img_staff_head_pic;
    TextView tv_group_staff_name;
    TextView tv_group_staff_postion;
    TextView tv_staff_intro;
    public GroupStaffHolder(@NonNull View itemView) {
        super(itemView);
        img_staff_head_pic = itemView.findViewById(R.id.img_staff_head_pic);
        tv_group_staff_name = itemView.findViewById(R.id.tv_froup_staff_name);
        tv_group_staff_postion = itemView.findViewById(R.id.tv_group_staff_postion);
        tv_staff_intro = itemView.findViewById(R.id.tv_staff_intro);
    }

}

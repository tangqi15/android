package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.group.R;

public class FindViewHolder extends RecyclerView.ViewHolder{

    TextView tv_find_item_screen_name;
    TextView tv_find_item_content;
    RecyclerView recycle_find_item_point_recycle;
    TextView tv_find_item_time;
    TextView tv_comment_number;
    ImageView img_find_item_head;
    LinearLayout line_comment;
    public FindViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_find_item_screen_name = itemView.findViewById(R.id.tv_find_item_screen_name);
        tv_find_item_content = itemView.findViewById(R.id.tv_find_item_content);
        recycle_find_item_point_recycle = itemView.findViewById(R.id.recycle_find_item_point_recycle);
        tv_find_item_time = itemView.findViewById(R.id.tv_find_item_time);
        img_find_item_head = itemView.findViewById(R.id.img_find_item_head);
        tv_comment_number = itemView.findViewById(R.id.tv_comment_number);
        line_comment = itemView.findViewById(R.id.line_comment);
    }
}
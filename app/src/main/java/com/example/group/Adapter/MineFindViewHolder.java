package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.group.R;

public class MineFindViewHolder extends RecyclerView.ViewHolder{


    TextView tv_mine_find_item_content;
    RecyclerView recycle_mine_find_item_point_recycle;
    TextView tv_mine_find_item_time;
    TextView tv_mine_comment_number;
    ImageView img_find_item_head;
    LinearLayout mine_line_comment;
    public MineFindViewHolder(@NonNull View itemView) {
        super(itemView);

        tv_mine_find_item_content = itemView.findViewById(R.id.tv_mine_find_item_content);
        recycle_mine_find_item_point_recycle = itemView.findViewById(R.id.recycle_mine_find_item_point_recycle);
        tv_mine_find_item_time = itemView.findViewById(R.id.tv_mine_find_item_time);
        img_find_item_head = itemView.findViewById(R.id.img_find_item_head);
        tv_mine_comment_number = itemView.findViewById(R.id.tv_mine_comment_number);
        mine_line_comment = itemView.findViewById(R.id.mine_line_comment);
    }
}
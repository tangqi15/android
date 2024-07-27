package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.group.R;

public class PersonPublicTopicItemViewHolder extends RecyclerView.ViewHolder{

    TextView tv_person_public_item_content;
    RecyclerView recycle_person_public_item_point_recycle;
    TextView tv_person_public_item_time;
    LinearLayout line_person_public_comment;
    TextView tv_person_public_item_number;
    public PersonPublicTopicItemViewHolder(@NonNull View itemView) {
        super(itemView);
        recycle_person_public_item_point_recycle = itemView.findViewById(R.id.recycle_person_public_item_point_recycle);
        tv_person_public_item_time = itemView.findViewById(R.id.tv_person_public_item_time);
        tv_person_public_item_content = itemView.findViewById(R.id.tv_person_public_item_content);
        line_person_public_comment = itemView.findViewById(R.id.line_person_public_comment);
        tv_person_public_item_number = itemView.findViewById(R.id.tv_person_public_item_number);
    }
}
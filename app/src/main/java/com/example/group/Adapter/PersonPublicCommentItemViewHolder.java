package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.group.R;

public class PersonPublicCommentItemViewHolder extends RecyclerView.ViewHolder{

    TextView tv_person_public_comment_content;
    TextView tv_person_public_comment_time;
    public PersonPublicCommentItemViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_person_public_comment_content = itemView.findViewById(R.id.tv_person_public_comment_content);
        tv_person_public_comment_time = itemView.findViewById(R.id.tv_person_public_comment_time);
    }
}
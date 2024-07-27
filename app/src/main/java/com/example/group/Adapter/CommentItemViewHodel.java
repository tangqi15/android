package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;

public class CommentItemViewHodel  extends RecyclerView.ViewHolder{
    ImageView img_comment_head;
    TextView tv_user_screen,tv_comment_time;
    TextView tv_comment_content;
    public CommentItemViewHodel(@NonNull View itemView) {
        super(itemView);
        img_comment_head = (ImageView)itemView.findViewById(R.id.img_comment_head);
        tv_user_screen = (TextView)itemView.findViewById(R.id.tv_user_screen);
        tv_comment_time = (TextView)itemView.findViewById(R.id.tv_comment_time);
        tv_comment_content = (TextView)itemView.findViewById(R.id.tv_comment_content);
    }
}
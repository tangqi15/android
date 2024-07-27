package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;

public class FaTieHolder extends RecyclerView.ViewHolder{

    TextView tv_tie_zi_title;
    TextView tv_tie_zi_content;
    TextView tv_tie_zi_screen;
    ImageView img_tie_zi_pic;
    public FaTieHolder(@NonNull View itemView) {
        super(itemView);
        tv_tie_zi_title = itemView.findViewById(R.id.tv_tie_zi_title);
        tv_tie_zi_content = itemView.findViewById(R.id.tv_tie_zi_content);
        tv_tie_zi_screen = itemView.findViewById(R.id.tv_tie_zi_screen);
        img_tie_zi_pic = itemView.findViewById(R.id.img_tie_zi_pic);
    }
}
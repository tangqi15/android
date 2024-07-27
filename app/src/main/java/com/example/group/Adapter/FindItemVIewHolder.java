package com.example.group.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.group.R;

public class FindItemVIewHolder extends RecyclerView.ViewHolder{

    ImageView fic;
    public FindItemVIewHolder(@NonNull View itemView) {
        super(itemView);
        fic = itemView.findViewById(R.id.fic);


    }
}
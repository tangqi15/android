package com.example.group.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FindItemAdapter extends RecyclerView.Adapter {
    Context context;
    List<String> itemitems;
    int proint_item_item_image;
    FindItemVIewHolder findItemVIewHolder;

    public FindItemAdapter(Context context, List<String> itemitems, int proint_item_item_image) {
        this.context = context;
        this.itemitems = itemitems;
        this.proint_item_item_image = proint_item_item_image;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.find_item_imgae3, viewGroup, false);
        findItemVIewHolder = new FindItemVIewHolder(itemView);
        return findItemVIewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        findItemVIewHolder = (FindItemVIewHolder) holder;
        itemitems.get(i);
        Log.e("Tag", "元素i的值" + itemitems.get(i));
        Picasso.with(context).
                load(Server.BASE_URL+"neu_soft_group/public/static/find_poster/"
                        + itemitems.get(i) + ".jpg").resize(400,500).into(findItemVIewHolder.fic);

    }

    @Override
    public int getItemCount() {
        if (itemitems == null){
            return 0;
        }else{
            return itemitems.size();
        }
    }
}
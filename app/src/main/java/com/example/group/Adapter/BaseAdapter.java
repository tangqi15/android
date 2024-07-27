package com.example.group.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {
    Context context;
    List<T> items;
    int layoutResource;


    public BaseAdapter(Context context, List<T> items, int layoutResource){
        this.context = context;
        this.items = items;
        this.layoutResource = layoutResource;
    }

    @Override
    public int getItemCount() {
        if (items == null){
            return 0;
        }else{
            return items.size();
        }
    }

    public void setList(List<T> list) {

        this.items=list;
        notifyDataSetChanged();
    }
}

package com.example.group.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Activity.GroupDetailsActivity;
import com.example.group.Bean.AllGroupBean;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GroupCollectionAdapter extends BaseAdapter<AllGroupBean>{

    GroupViewHolder groupViewHolder;


    public GroupCollectionAdapter(Context context, List<AllGroupBean> items, int layoutResource) {
        super(context, items, layoutResource);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.group_item,viewGroup,false);
        groupViewHolder = new GroupViewHolder(itemView);
        return groupViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        groupViewHolder = (GroupViewHolder) holder;
        final String group_id = items.get(i).getGroup_id();
        final String group_pic = items.get(i).getGroup_pic();
        final String group_name = items.get(i).getGroup_name();
        final String group_intro = items.get(i).getGroup_intro();
        final String group_type = items.get(i).getGroup_type_name();
        //http://10.0.2.2/neusoft_group/public/group_pic//shijue.jpg

        Picasso.with(context).load(Server.BASE_URL+"neu_soft_group/public/static/group_pic/"
                +group_pic).into(groupViewHolder.img_group_pic);
        groupViewHolder.tv_group_name.setText(group_name);

        groupViewHolder.tv_group_type.setText(group_type);
        if(group_intro.length()>13){
            String group_intro_easy = group_intro.substring(0,13);
            groupViewHolder.tv_group_intro.setText(group_intro_easy+"...");
        }else{
            groupViewHolder.tv_group_intro.setText(group_intro);
        }
        groupViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GroupDetailsActivity.class);
                intent.putExtra("group_id",group_id);
                intent.putExtra("group_pic",group_pic);
                intent.putExtra("group_name",group_name);
                intent.putExtra("group_intro",group_intro);
                intent.putExtra("group_type",group_type);
                context.startActivity(intent);

            }
        });
    }
}

package com.example.group.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Activity.EventDetailsActivity;
import com.example.group.Bean.ActivityBean;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GroupActivityAdapter extends BaseAdapter<ActivityBean> {
    GroupActivityHolder groupActivityHolder;
    //设置  向下一个页面 传值的   变量
     String activity_id;
     String activity_name ;
     String activity_start_time ;
     String activity_finish_time ;
     String activity_address;
     String activity_pic ;
     String activity_intro ;
     //社团ID
     String group_id ;
    public GroupActivityAdapter(Context context, List<ActivityBean> items, int layoutResource) {
        super(context, items, layoutResource);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.group_activity_item,viewGroup,false);
        groupActivityHolder = new GroupActivityHolder(itemView);
        return groupActivityHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        groupActivityHolder= (GroupActivityHolder) viewHolder;
        ActivityBean activityBean = items.get(i);

        activity_id = activityBean.getActivity_id();
        activity_name = activityBean.getActivity_name();
        activity_start_time = activityBean.getActivity_start_time();
        activity_finish_time = activityBean.getActviity_finish_time();
        activity_address = activityBean.getActivity_address();
        activity_pic = activityBean.getActivity_pic();
        activity_intro = activityBean.getActivity_intro();
        group_id = activityBean.getGroup_id();
        final String activity_is_apply = activityBean.getActivity_is_apply();
        groupActivityHolder.tv_group_activity_start_time.setText("  "+activity_start_time);
        groupActivityHolder.tv_group_activity_finish_time.setText("  "+activity_finish_time);
        groupActivityHolder.tv_group_activity_address.setText("  "+activity_address);
        groupActivityHolder.tv_activity_name.setText(" "+activity_name);
        Picasso.with(context).load(Server.BASE_URL+"neu_soft_group/public/static/activity_pic/"+activity_pic).resize(150,150).
                into(groupActivityHolder.img_group_activity_poster);
        groupActivityHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailsActivity.class);
                intent.putExtra("activity_id",activity_id);
                intent.putExtra("activity_name",activity_name);
                intent.putExtra("activity_address",activity_address);
                intent.putExtra("activity_pic",activity_pic);
                intent.putExtra("activity_start_time",activity_start_time);
                intent.putExtra("activity_finish_time",activity_finish_time);
                intent.putExtra("activity_intro",activity_intro);
                intent.putExtra("group_id",group_id);
                intent.putExtra("activity_is_apply",activity_is_apply);
                context.startActivity(intent);
            }
        });
    }
}

package com.example.group.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Activity.EventDetailsActivity;
import com.example.group.Bean.ActivityBean;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends BaseAdapter<ActivityBean> {


    EventHolder eventHolder;

    public EventAdapter(Context context, List<ActivityBean> items, int group_item) {
        super(context, items, group_item);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_event_items,viewGroup,false);
        eventHolder = new EventHolder(itemView);
        return eventHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        eventHolder= (EventHolder) viewHolder;
        ActivityBean activityBean = items.get(i);
        final String activity_id = activityBean.getActivity_id();
        final String activity_name = activityBean.getActivity_name();
        final String activity_start_time = activityBean.getActivity_start_time();
        final String activity_finish_time = activityBean.getActviity_finish_time();
        final String activity_address = activityBean.getActivity_address();
        final String activity_pic = activityBean.getActivity_pic();
        final String activity_intro = activityBean.getActivity_intro();
        final String group_id = activityBean.getGroup_id();
        final String activity_is_apply = activityBean.getActivity_is_apply();

       /* eventHolder.tv_activity_start_time.setText("  "+activity_start_time);*/
        eventHolder.tv_activity_finish_time.setText("  "+activity_finish_time);
        eventHolder.tv_activity_address.setText("  "+activity_address);
        eventHolder.tv_group_activity_name.setText("  "+activity_name);

       // http://10.0.2.2/neu_soft_group/public/activity_pic/1.jpg
        Picasso.with(context)
                .load(Server.BASE_URL+"neu_soft_group/public/static/activity_pic/"+activity_pic)
                .resize(150,150)
                .into(eventHolder.img_activity_pic);

        //活动详情页面
        eventHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailsActivity.class);
                intent.putExtra("activity_id",activity_id);
                intent.putExtra("activity_name",activity_name);
                intent.putExtra("activity_pic",activity_pic);
                intent.putExtra("activity_address",activity_address);
                intent.putExtra("activity_intro",activity_intro);
                intent.putExtra("activity_start_time",activity_start_time);
                intent.putExtra("activity_finish_time",activity_finish_time);
                intent.putExtra("group_id",group_id);
                intent.putExtra("activity_is_apply",activity_is_apply);
                context.startActivity(intent);
            }
        });
    }


}

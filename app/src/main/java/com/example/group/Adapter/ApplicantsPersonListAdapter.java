package com.example.group.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.group.Activity.PersonPublicActivity;
import com.example.group.Bean.ApplicantsPersonListBean;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ApplicantsPersonListAdapter extends BaseAdapter{
    ApplicantsPersonListHolder applicantsPersonListHolder;

    public ApplicantsPersonListAdapter(Context context, List items, int layoutResource) {
        super(context, items, layoutResource);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.event_details_item,viewGroup,false);
        applicantsPersonListHolder = new ApplicantsPersonListHolder(itemView);
        return applicantsPersonListHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        applicantsPersonListHolder= (ApplicantsPersonListHolder) viewHolder;
        ApplicantsPersonListBean applicantsPersonListBean = (ApplicantsPersonListBean) items.get(i);

        final String user_id = applicantsPersonListBean.getUser_id();
        final String user_screen_name = applicantsPersonListBean.getScreen();
        final String user_pic = applicantsPersonListBean.getUser_pic();
        final String group_name = applicantsPersonListBean.getGroup_name();

        applicantsPersonListHolder.tv_event_details_item_screen.setText(user_screen_name);
        applicantsPersonListHolder.tv_event_details_item_time.setText(applicantsPersonListBean.getActivity_order_time());
        Picasso.with(context).load(Server.BASE_URL+"neu_soft_group/public/static/user_head/"+applicantsPersonListBean.getUser_pic()).resize(150,150)
                .into(applicantsPersonListHolder.img_event_details_item_poster);

        applicantsPersonListHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PersonPublicActivity.class);
                intent.putExtra("user_id",user_id);
                intent.putExtra("user_pic",user_pic);
                intent.putExtra("user_screen_name",user_screen_name);
                intent.putExtra("group_name",group_name);
                context.startActivity(intent);
            }
        });
    }
}

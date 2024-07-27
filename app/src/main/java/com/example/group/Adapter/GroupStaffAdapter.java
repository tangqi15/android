package com.example.group.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Bean.GroupStaffBean;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GroupStaffAdapter extends BaseAdapter<GroupStaffBean> {
    GroupStaffHolder groupStaffHolder;
    public GroupStaffAdapter(Context context, List<GroupStaffBean> items, int layoutResource) {
        super(context, items, layoutResource);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.group_staff_item,viewGroup,false);
        groupStaffHolder = new GroupStaffHolder(itemView);
        return groupStaffHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        groupStaffHolder= (GroupStaffHolder) viewHolder;
        GroupStaffBean groupStaffBean = items.get(i);
        String group_staff_name = groupStaffBean.getGroup_staff_name();
        String group_staff_position = groupStaffBean.getGroup_post_type_name();
        String group_staff_pic = groupStaffBean.getGroup_staff_pic();
        String tv_staff_intro = groupStaffBean.getGroup_staff_intro();
        groupStaffHolder.tv_group_staff_name.setText(group_staff_name);
        groupStaffHolder.tv_group_staff_postion.setText(group_staff_position);
        groupStaffHolder.tv_staff_intro.setText(tv_staff_intro);
        Picasso.with(context).load(Server.BASE_URL+"neu_soft_group/public/static/group_staff_pic/"
                +group_staff_pic).resize(300,300).into(groupStaffHolder.img_staff_head_pic);
    }
}

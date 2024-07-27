package com.example.group.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Activity.FindItemDetailsActivity;
import com.example.group.Activity.PersonPublicActivity;
import com.example.group.Bean.CommentListBean;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentListAdapter extends BaseAdapter<CommentListBean> {
    CommentItemViewHodel commentItemViewHolder;
    public CommentListAdapter(Context context, List<CommentListBean> items, int layoutResource) {
        super(context, items, layoutResource);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.comment_item,viewGroup,false);
        commentItemViewHolder = new CommentItemViewHodel(itemView);
        return commentItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        commentItemViewHolder= (CommentItemViewHodel) viewHolder;
        CommentListBean commentListBean = items.get(i);
        commentItemViewHolder.tv_user_screen.setText(commentListBean.getScreen());
        commentItemViewHolder.tv_comment_time.setText(commentListBean.getFind_comment_time());
        commentItemViewHolder.tv_comment_content.setText(commentListBean.getFind_comment_content());
        Picasso.with(context).load(Server.BASE_URL+"neu_soft_group/public/static/user_head/"+commentListBean.getUser_pic())
                .into(commentItemViewHolder.img_comment_head);
        final String user_id = commentListBean.getUser_id();
        commentItemViewHolder.img_comment_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PersonPublicActivity.class);
                intent.putExtra("user_id",user_id);
                context.startActivity(intent);
            }
        });
    }

}

package com.example.group.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Activity.FindItemDetailsActivity;
import com.example.group.Bean.PersonPublicCommentBean;
import com.example.group.R;
import java.util.List;

public class PersonPublicCommentAdapter extends BaseAdapter<PersonPublicCommentBean> {
    PersonPublicCommentItemViewHolder personPublicCommentItemViewHolder;
    public PersonPublicCommentAdapter(Context context, List<PersonPublicCommentBean> items, int layoutResource) {
        super(context, items, layoutResource);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_person_public_comment_item,viewGroup,false);
        personPublicCommentItemViewHolder = new PersonPublicCommentItemViewHolder(itemView);
        return personPublicCommentItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        personPublicCommentItemViewHolder = (PersonPublicCommentItemViewHolder) viewHolder;
        PersonPublicCommentBean personPublicCommentBean = items.get(i);
        final String find_id = personPublicCommentBean.getFind_id();
        final String user_id = personPublicCommentBean.getUser_id();
        personPublicCommentItemViewHolder.tv_person_public_comment_content.setText(personPublicCommentBean.getFind_comment_content());
        personPublicCommentItemViewHolder.tv_person_public_comment_time.setText(personPublicCommentBean.getFind_comment_time());

        personPublicCommentItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FindItemDetailsActivity.class);
                intent.putExtra("find_id",find_id);
                intent.putExtra("user_id",user_id);
                context.startActivity(intent);
            }
        });
    }

}

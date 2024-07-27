package com.example.group.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Activity.FindItemDetailsActivity;
import com.example.group.Bean.PersonPublicTopicBean;
import com.example.group.R;
import com.example.group.util.FullyGridLayoutManager;

import java.util.Arrays;
import java.util.List;

public class PersonPublicTopicAdapter extends BaseAdapter<PersonPublicTopicBean> {
    PersonPublicTopicItemViewHolder personPublicTopicItemViewHolder;
    List<String> itemItems;
    public PersonPublicTopicAdapter(Context context, List<PersonPublicTopicBean> items, int layoutResource) {
        super(context, items, layoutResource);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_person_public_topic_item,viewGroup,false);
        personPublicTopicItemViewHolder = new PersonPublicTopicItemViewHolder(itemView);
        return personPublicTopicItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        personPublicTopicItemViewHolder = (PersonPublicTopicItemViewHolder) viewHolder;
        PersonPublicTopicBean personPublicTopicBean = items.get(i);
        String find_pic = personPublicTopicBean.getFind_pic();
       final String user_id = personPublicTopicBean.getUser_id();
       final
       String find_id = personPublicTopicBean.getFind_id();
        personPublicTopicItemViewHolder.tv_person_public_item_content.setText(personPublicTopicBean.getFind_intro());
        personPublicTopicItemViewHolder.tv_person_public_item_time.setText(personPublicTopicBean.getFind_addtime());
        personPublicTopicItemViewHolder.tv_person_public_item_number.setText(personPublicTopicBean.getComment_sum());
        personPublicTopicItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FindItemDetailsActivity.class);
                intent.putExtra("user_id",user_id);
                intent.putExtra("find_id",find_id);
                context.startActivity(intent);
            }
        });
        itemItems = Arrays.asList(find_pic.split("\\.jpg"));
        int a  = itemItems.size();
        if(a == 0 ){
            initPic1();
        }if(a == 1){
            initPic1();
        }if(a == 2){
            initPic2();
        }if (a > 2){
            initPic3();
        }
    }
    private void initPic1() {
        FullyGridLayoutManager manager = new
                FullyGridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);

        personPublicTopicItemViewHolder.recycle_person_public_item_point_recycle.setLayoutManager(manager);
        //itemitems表示数组长度
        FindItemAdapter1 findItemAdapter1 = new FindItemAdapter1(context,itemItems,R.layout.find_item_imgae1);
        personPublicTopicItemViewHolder.recycle_person_public_item_point_recycle.setAdapter(findItemAdapter1);
    }
    private void initPic2() {
        FullyGridLayoutManager manager = new
                FullyGridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);

        personPublicTopicItemViewHolder.recycle_person_public_item_point_recycle.setLayoutManager(manager);
        //itemitems表示数组长度
        FindItemAdapter2 findItemAdapter2 = new FindItemAdapter2(context,itemItems,R.layout.find_item_imgae2);
        personPublicTopicItemViewHolder.recycle_person_public_item_point_recycle.setAdapter(findItemAdapter2);
    }
    private void initPic3() {
        FullyGridLayoutManager manager = new
                FullyGridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);

        personPublicTopicItemViewHolder.recycle_person_public_item_point_recycle.setLayoutManager(manager);
        //itemitems表示数组长度
        FindItemAdapter findItemAdapter = new FindItemAdapter(context,itemItems,R.layout.find_item_imgae3);
        personPublicTopicItemViewHolder.recycle_person_public_item_point_recycle.setAdapter(findItemAdapter);


    }


}

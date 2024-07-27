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
import com.example.group.Activity.PersonPublicActivity;
import com.example.group.Bean.FindBean;
import com.example.group.R;
import com.example.group.Server.Server;
import com.example.group.util.FullyGridLayoutManager;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class FindAdapter extends BaseAdapter<FindBean> {

    FindViewHolder findViewHolder;
    List<String> itemItems;

    public FindAdapter(Context context, List<FindBean> items, int group_item) {
        super(context, items, group_item);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_find_items,viewGroup,false);
        findViewHolder = new FindViewHolder(itemView);
        return findViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        findViewHolder= (FindViewHolder) viewHolder;
        FindBean findBean = items.get(i);
        final String find_id = findBean.getFind_id();
        final String user_id = findBean.getUser_id();
        final String find_addtime = findBean.getFind_addtime();
        final String find_intro = findBean.getFind_intro();
        final String user_screen_name = findBean.getScreen();
        final String user_pic = findBean.getUser_pic();
        final String find_pic = findBean.getFind_pic();
       findViewHolder.tv_find_item_content.setText("  "+find_intro);
       findViewHolder.tv_find_item_time.setText("  "+find_addtime);
       findViewHolder.tv_find_item_screen_name.setText("  "+user_screen_name);
       findViewHolder.tv_comment_number.setText(" "+findBean.getComment_sum());

        Picasso.with(context).
                //http://172.20.10.6/neu_soft_group/public/static/user_head/1.jpg
                load(Server.BASE_URL+"neu_soft_group/public/static/user_head/"+user_pic).
                into(findViewHolder.img_find_item_head);

        itemItems = Arrays.asList(find_pic.split("\\.jpg"));
            int a  = itemItems.size();
        if(a == 0 ){
                initPic1();
        }if(a == 1){
                initPic1();
        }if(a == 2||a == 4){
                initPic2();
        }if (a == 3||a > 4){
                initPic3();
        }
        findViewHolder.img_find_item_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PersonPublicActivity.class);
                intent.putExtra("user_id",user_id);
                context.startActivity(intent);
            }
        });

        findViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FindItemDetailsActivity.class);
                intent.putExtra("user_screen_name",user_screen_name);
                intent.putExtra("user_id",user_id);
                intent.putExtra("find_intro",find_intro);
                intent.putExtra("user_pic",user_pic);
                intent.putExtra("find_pic",find_pic);
                intent.putExtra("find_addtime",find_addtime);
                intent.putExtra("find_id",find_id);
                context.startActivity(intent);
            }
        });



    }

    private void initPic1() {
        FullyGridLayoutManager manager = new
                FullyGridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false);

        findViewHolder.recycle_find_item_point_recycle.setLayoutManager(manager);
        //itemitems表示数组长度
        FindItemAdapter1 findItemAdapter1 = new FindItemAdapter1(context,itemItems,R.layout.find_item_imgae1);
        findViewHolder.recycle_find_item_point_recycle.setAdapter(findItemAdapter1);
    }
    private void initPic2() {
        FullyGridLayoutManager manager = new
                FullyGridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);

        findViewHolder.recycle_find_item_point_recycle.setLayoutManager(manager);
        //itemitems表示数组长度
        FindItemAdapter2 findItemAdapter2 = new FindItemAdapter2(context,itemItems,R.layout.find_item_imgae2);
        findViewHolder.recycle_find_item_point_recycle.setAdapter(findItemAdapter2);
    }
    private void initPic3() {
        FullyGridLayoutManager manager = new
                FullyGridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);

        findViewHolder.recycle_find_item_point_recycle.setLayoutManager(manager);
        //itemitems表示数组长度
        FindItemAdapter findItemAdapter = new FindItemAdapter(context,itemItems,R.layout.find_item_imgae3);
        findViewHolder.recycle_find_item_point_recycle.setAdapter(findItemAdapter);


    }
}

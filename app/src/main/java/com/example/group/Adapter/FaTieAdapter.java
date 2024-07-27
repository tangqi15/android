package com.example.group.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Bean.FaTieBean;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;
import java.util.List;

public class FaTieAdapter extends BaseAdapter<FaTieBean> {

    FaTieHolder faTieHolder;

    public FaTieAdapter(Context context, List<FaTieBean> items, int layoutResource) {
        super(context, items, layoutResource);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fragment_fatie_item,viewGroup,false);
        faTieHolder = new FaTieHolder(itemView);
        return faTieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        faTieHolder= (FaTieHolder) holder;
        FaTieBean faTieBean = items.get(i);
        final String fa_tie_id = faTieBean.getPost_message_id();
        final String fa_tie_title = faTieBean.getPost_message_title();
        final String fa_tie_intro = faTieBean.getPost_message_content();
        final String fa_tie_screen = faTieBean.getScreen();
        final String fa_tie_pic = faTieBean.getUser_pic();

        faTieHolder.tv_tie_zi_title.setText(fa_tie_title);
        faTieHolder.tv_tie_zi_screen.setText(fa_tie_screen);
        Picasso.with(context).load(Server.BASE_URL+"neu_soft_group/public/static/posters/"+fa_tie_pic).resize(150,150).
                into(faTieHolder.img_tie_zi_pic);
        if(fa_tie_intro.length()>20){
            String activity_intro1 = fa_tie_intro.substring(0,20);
            faTieHolder.tv_tie_zi_content.setText(activity_intro1+"...");
        }else if (fa_tie_intro.equals("")){
            faTieHolder.tv_tie_zi_content.setText("暂无内容");
        }else{
            faTieHolder.tv_tie_zi_content.setText(fa_tie_intro);
        }
    }

}

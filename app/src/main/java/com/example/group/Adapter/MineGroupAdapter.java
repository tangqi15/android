package com.example.group.Adapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.group.Activity.GroupDetailsActivity;
import com.example.group.Activity.Main2Activity;
import com.example.group.Bean.AllGroupBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.QuitGroupModel;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MineGroupAdapter extends BaseAdapter<AllGroupBean> {

        MineGroupViewHolder minegroupViewHolder;
        String user_id;
        public MineGroupAdapter(Context context, List<AllGroupBean> items, int layoutResource,String user_ids) {
            super(context, items, layoutResource);
             this.user_id = user_ids;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.mine_group_item,viewGroup,false);
            minegroupViewHolder = new MineGroupViewHolder(itemView);
            return minegroupViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
            minegroupViewHolder = (MineGroupViewHolder) holder;
            final String group_id    = items.get(i).getGroup_id();
            final String group_pic   = items.get(i).getGroup_pic();
            final String group_name  = items.get(i).getGroup_name();
            final String group_intro = items.get(i).getGroup_intro();
            final String group_type  = items.get(i).getGroup_type_name();
            //http://10.0.2.2/neusoft_group/public/group_pic//shijue.jpg
            Log.e("Tag","user_id"+""+user_id);
            Picasso.with(context).load(Server.BASE_URL+"neu_soft_group/public/static/group_pic/"
                    +group_pic).into(minegroupViewHolder.img_group_pic);
            minegroupViewHolder.tv_group_name.setText(group_name);
            minegroupViewHolder.tv_group_type.setText(group_type);
            if(group_intro.length()>13){
                String group_intro_easy = group_intro.substring(0,13);
                minegroupViewHolder.tv_group_intro.setText(group_intro_easy+"...");
            }else{
                minegroupViewHolder.tv_group_intro.setText(group_intro);
            }

            minegroupViewHolder.btn_quit_group.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context).setTitle("系统提示")
                            //设置显示的内容
                            .setMessage("是否要 推出社团！")
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                                @Override
                                public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                    //退出社团接口
                     QuitGroupModel quitGroupModel = new QuitGroupModel();

                     quitGroupModel.getQuitGroupMessage(user_id,listener);

                                }
                            }).setNegativeButton("不确定", new DialogInterface.OnClickListener() {//添加返回按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//响应事件
                            Log.i("alertdialog"," 请保存数据！");
                        }
                    }).show();//在按键响应事件中显示此对话框
                }

                TListener<SuccessBean> listener = new TListener<SuccessBean>() {
                    @Override
                    public void onResponse(SuccessBean successBean) {
                        if (successBean.getSuccess().equals("1")){
                            Toast.makeText(context,"退出社团成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, Main2Activity.class);
                            context.startActivity(intent);
                        }else{
                            Toast.makeText(context,"退出社团失败，请稍后再试",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFail(String msg) {
                        Toast.makeText(context,"退出社团失败，请检查网络",Toast.LENGTH_SHORT).show();
                    }
                };
            });


            minegroupViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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


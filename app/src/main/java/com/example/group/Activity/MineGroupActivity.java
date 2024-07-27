package com.example.group.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.group.Adapter.MineGroupAdapter;
import com.example.group.Bean.AllGroupBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.GroupModel;
import com.example.group.R;

import java.util.List;

public class MineGroupActivity extends BaseActivity {

    MineGroupAdapter mineGroupAdapter;
    RecyclerView mine_group_recycle;
ImageView img_mine_group_back;
    String user_id;

    private List<AllGroupBean> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_group_actviity);
        initView();
        initEvent();
        initSend();
    }

    private void initSend() {
        GroupModel groupModel = new GroupModel();

        groupModel.getMineGroupList(user_id,mineListListener);
    }


    private void initView() {
        mine_group_recycle = findViewById(R.id.mine_group_recycle);
        img_mine_group_back = findViewById(R.id.img_mine_group_back);
        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MineGroupActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mine_group_recycle.setLayoutManager(linearLayoutManager);
        user_id = getUser_id();
        Log.e("Tag","user_id1"+""+user_id);
        mineGroupAdapter = new MineGroupAdapter(MineGroupActivity.this,items,R.layout.mine_group_item,user_id);
        Log.e("Tag","user_id2"+""+user_id);
        mine_group_recycle.setAdapter(mineGroupAdapter);
        mine_group_recycle.setItemAnimator(new DefaultItemAnimator());
    }
    private void initEvent() {
        img_mine_group_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
ListListener<AllGroupBean>  mineListListener = new ListListener<AllGroupBean>() {
    @Override
    public void onResponse(List<AllGroupBean> list) {
        mineGroupAdapter.setList(list);
    }

    @Override
    public void onFail(String msg) {

    }
};
    public String getUser_id() {
        SharedPreferences sp = MineGroupActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
}

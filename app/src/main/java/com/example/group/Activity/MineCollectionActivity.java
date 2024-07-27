package com.example.group.Activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.group.Adapter.GroupCollectionAdapter;
import com.example.group.Bean.AllGroupBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.GroupModel;
import com.example.group.R;

import java.util.List;

public class MineCollectionActivity extends BaseActivity {
RecyclerView recycle_collection;
ImageView img_collection_back;
    private List<AllGroupBean> items;
    GroupCollectionAdapter groupCollectionAdapter;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initView();
        initSend();
        initEvent();
    }




    private void initView() {
        recycle_collection = findViewById(R.id.recycle_collection);
        img_collection_back = findViewById(R.id.img_collection_back);

        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MineCollectionActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_collection.setLayoutManager(linearLayoutManager);
        groupCollectionAdapter = new GroupCollectionAdapter(MineCollectionActivity.this,items,R.layout.group_item);
        recycle_collection.setAdapter(groupCollectionAdapter);
        recycle_collection.setItemAnimator(new DefaultItemAnimator());
    }

    private void initSend() {
        GroupModel groupModel = new GroupModel();
        user_id = getUser_id();
        groupModel.getGroupCollectionList(user_id,listlistener);
    }
    private void initEvent() {
        img_collection_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    ListListener<AllGroupBean> listlistener = new ListListener<AllGroupBean>() {
        @Override
        public void onResponse(List<AllGroupBean> list) {
            groupCollectionAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Log.e("Tag","这是访问失败了么");
        }
    };



    public String getUser_id() {
        SharedPreferences sp = MineCollectionActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
}

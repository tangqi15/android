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

import com.example.group.Adapter.MineFindAdapter;
import com.example.group.Bean.FindBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.FindModel;
import com.example.group.R;

import java.util.List;

public class MineFindActivity extends BaseActivity{
    RecyclerView recycle_mine_find;
    private List<FindBean> items;
    MineFindAdapter mineFindAdapter;
    ImageView img_mine_find_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_find);
        initView();
        initSend();
        initEvent();
    }



    private void initView() {
        recycle_mine_find = findViewById(R.id.recycle_mine_find);
        img_mine_find_back = findViewById(R.id.img_mine_find_back);
        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MineFindActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_mine_find.setLayoutManager(linearLayoutManager);
        mineFindAdapter = new MineFindAdapter(MineFindActivity.this,items,R.layout.mine_find_items);
        recycle_mine_find.setAdapter(mineFindAdapter);
        recycle_mine_find.setItemAnimator(new DefaultItemAnimator());
    }

    private void initEvent() {
        img_mine_find_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initSend() {
        FindModel findModel = new FindModel();
        String user_id = getUser_id();
        findModel.getMineFind(user_id,listListener);
    }
    ListListener<FindBean> listListener = new ListListener<FindBean>() {
        @Override
        public void onResponse(List<FindBean> list) {
            mineFindAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Log.e("Tag","这是访问失败了么");
        }
    };

    public String getUser_id() {
        SharedPreferences sp = MineFindActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
}

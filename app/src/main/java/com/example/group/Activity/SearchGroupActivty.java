package com.example.group.Activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.group.Adapter.GroupAdapter;
import com.example.group.Bean.AllGroupBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.SearchModel;
import com.example.group.R;
import java.util.List;

public class SearchGroupActivty extends BaseActivity {
    EditText edt_search_name;
    ImageView img_submit_search;
    RecyclerView search_recycle;
    GroupAdapter groupAdapter;
    ImageView img_search_group_back;
    List<AllGroupBean> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_group_activty);
        initView();
        initEvent();
    }

    private void initView() {
        edt_search_name = findViewById(R.id.edt_search_name);
        img_submit_search = findViewById(R.id.img_submit_search);
        search_recycle = findViewById(R.id.search_recycle);
        img_search_group_back = findViewById(R.id.img_search_group_back);
        //设置布局类型
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchGroupActivty.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        search_recycle.setLayoutManager(linearLayoutManager);

        groupAdapter = new GroupAdapter(SearchGroupActivty.this, items, R.layout.group_item);
        search_recycle.setAdapter(groupAdapter);
        search_recycle.setItemAnimator(new DefaultItemAnimator());
    }

    private void initEvent() {
        img_submit_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_name = edt_search_name.getText().toString();
                if("".equals(search_name)){
                    Toast.makeText(SearchGroupActivty.this,"请输入查询内容",Toast.LENGTH_SHORT).show();
                }else{
                    SearchModel searchModel = new SearchModel();
                    searchModel.getSearchList(search_name,searchList);
                }
            }
        });
        img_search_group_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    ListListener<AllGroupBean> searchList = new ListListener<AllGroupBean>() {
        @Override
        public void onResponse(List<AllGroupBean> list) {
            groupAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {

        }
    };
}


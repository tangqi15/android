package com.example.group.Fragment.Fragment_Mine_Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Adapter.EventAdapter;
import com.example.group.Bean.ActivityBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.ActivityModel;
import com.example.group.R;

import java.util.List;

public class Fragment_mine_activity_finish extends Fragment {
    //活动已经完成
    View view = null;
    RecyclerView mine_activity_finish_recycle;
    private List<ActivityBean> items;
    EventAdapter eventAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine_activity_finish, container, false);
        initView();
        initSend();
        return view;
    }

    private void initView() {
        mine_activity_finish_recycle = view.findViewById(R.id.mine_activity_finish_recycle);
        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mine_activity_finish_recycle.setLayoutManager(linearLayoutManager);
        eventAdapter = new EventAdapter(getActivity(),items,R.layout.fragment_event_items);
        mine_activity_finish_recycle.setAdapter(eventAdapter);
        mine_activity_finish_recycle.setItemAnimator(new DefaultItemAnimator());
    }

    private void initSend() {
        ActivityModel activityModel = new ActivityModel();
        String user_id = getUser_id();
        activityModel.getMineActivityFinish(user_id,mine_activity_list_listener);
    }
    ListListener<ActivityBean> mine_activity_list_listener = new ListListener<ActivityBean>() {
        @Override
        public void onResponse(List<ActivityBean> list) {
            eventAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {

        }
    };
    public String getUser_id() {
        SharedPreferences sp = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
}

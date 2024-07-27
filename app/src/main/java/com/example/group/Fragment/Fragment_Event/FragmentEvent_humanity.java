package com.example.group.Fragment.Fragment_Event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Adapter.EventAdapter;
import com.example.group.Bean.ActivityBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.ActivityModel;
import com.example.group.R;

import java.util.List;

public class FragmentEvent_humanity extends Fragment {
    //人文
    View view = null;
    RecyclerView recycle_humanity;
    private List<ActivityBean> items;
    EventAdapter eventAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_humanity,container,false);
        initView();
        initSend();
        return view;
    }
    private void initView() {
        recycle_humanity = view.findViewById(R.id.recycle_humanity);

        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_humanity.setLayoutManager(linearLayoutManager);
        eventAdapter = new EventAdapter(getActivity(),items,R.layout.fragment_event_items);
        recycle_humanity.setAdapter(eventAdapter);
        recycle_humanity.setItemAnimator(new DefaultItemAnimator());
    }
    private void initSend() {
        ActivityModel activityModel = new ActivityModel();
        activityModel.getGroupActivity4(listListener);
    }
    ListListener<ActivityBean> listListener = new ListListener<ActivityBean>() {
        @Override
        public void onResponse(List<ActivityBean> list) {
            eventAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Log.e("Tag","这是访问失败了么");
        }
    };
}

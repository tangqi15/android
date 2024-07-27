package com.example.group.Fragment.Fragment_Group;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.example.group.Activity.GroupDetailsActivity;
import com.example.group.Adapter.GroupActivityAdapter;
import com.example.group.Bean.ActivityBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.GroupModel;
import com.example.group.R;

import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentGroup_activity  extends Fragment {
    //活动
    View view = null;
    RecyclerView recycle_group_activity;
    GroupActivityAdapter groupActivityAdapter;
    private List<ActivityBean> items;
    @SuppressLint("ValidFragment")
    public FragmentGroup_activity(GroupDetailsActivity groupDetailsActivity) {
        GroupModel groupModel = new GroupModel();
        String group_id = groupDetailsActivity.group_id;
        groupModel.getGroupActivityList(group_id,listListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_group_activity,container,false);
        InitView();
        return view;
    }

    private void InitView() {
        recycle_group_activity = view.findViewById(R.id.recycle_group_activity);
        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_group_activity.setLayoutManager(linearLayoutManager);
        groupActivityAdapter = new GroupActivityAdapter(getActivity(),items,R.layout.group_activity_item);
        recycle_group_activity.setAdapter(groupActivityAdapter);
        recycle_group_activity.setItemAnimator(new DefaultItemAnimator());
    }

    ListListener<ActivityBean> listListener = new ListListener<ActivityBean>() {
        @Override
        public void onResponse(List<ActivityBean> list) {
            groupActivityAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(getContext(),"请检查网络是否连接！！！",Toast.LENGTH_SHORT).show();
        }
    };
}

package com.example.group.Fragment.Fragment_Group;

import android.annotation.SuppressLint;
import android.nfc.Tag;
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
import android.widget.Toast;

import com.example.group.Activity.GroupDetailsActivity;
import com.example.group.Adapter.GroupStaffAdapter;
import com.example.group.Bean.GroupStaffBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.GroupModel;
import com.example.group.R;

import java.util.List;
@SuppressLint("ValidFragment")
public class FragmentGroup_staff  extends Fragment {
    //人员
    View view = null;
    RecyclerView recyecle_staff;
    GroupStaffAdapter groupStaffAdapter;
    private List<GroupStaffBean> items;

    @SuppressLint("ValidFragment")
    public FragmentGroup_staff(GroupDetailsActivity groupDetailsActivity) {
       GroupModel groupModel = new GroupModel();
        String group_id = groupDetailsActivity.group_id;
        groupModel.getGroupStaffList(group_id,listListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_group_staff,container,false);
        initView();
       // initSend();
        return view;
    }

    private void initView() {
        recyecle_staff = view.findViewById(R.id.recyecle_staff);

        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyecle_staff.setLayoutManager(linearLayoutManager);
        groupStaffAdapter = new GroupStaffAdapter(getActivity(),items,R.layout.group_staff_item);
        recyecle_staff.setAdapter(groupStaffAdapter);
        recyecle_staff.setItemAnimator(new DefaultItemAnimator());
    }


    ListListener<GroupStaffBean> listListener = new ListListener<GroupStaffBean>() {
        @Override
        public void onResponse(List<GroupStaffBean> list) {
            groupStaffAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(getContext(),"请检查网络是否连接！！！",Toast.LENGTH_SHORT).show();
        }
    };
}

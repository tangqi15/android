package com.example.group.Fragment.Fragment_Find;

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

import com.example.group.Adapter.FindAdapter;
import com.example.group.Bean.FindBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.FindModel;
import com.example.group.R;

import java.util.List;

public class FragmentFind_academic  extends Fragment {
    //学术
    View view = null;
    RecyclerView recycle_academic;
    private List<FindBean> items;
    FindAdapter findAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_academic,container,false);
        initView();
        initSend();
        return view;
    }



    private void initView() {
        recycle_academic = view.findViewById(R.id.recycle_academic);

        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_academic.setLayoutManager(linearLayoutManager);
        findAdapter = new FindAdapter(getActivity(),items,R.layout.fragment_find_items);
        recycle_academic.setAdapter(findAdapter);
        recycle_academic.setItemAnimator(new DefaultItemAnimator());
    }
    private void initSend() {
        FindModel findModel = new FindModel();
        findModel.getFindAcademic(listListener);
    }
    ListListener<FindBean> listListener = new ListListener<FindBean>() {
        @Override
        public void onResponse(List<FindBean> list) {
            findAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Log.e("Tag","这是访问失败了么");
        }
    };
}

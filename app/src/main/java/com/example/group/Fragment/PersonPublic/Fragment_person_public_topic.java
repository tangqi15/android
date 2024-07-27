package com.example.group.Fragment.PersonPublic;

import android.annotation.SuppressLint;
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

import com.example.group.Activity.PersonPublicActivity;
import com.example.group.Adapter.PersonPublicTopicAdapter;
import com.example.group.Bean.PersonPublicTopicBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.PersonPublicModel;
import com.example.group.R;

import java.util.List;
@SuppressLint("ValidFragment")
public class Fragment_person_public_topic extends Fragment {
    //发布的话题
    View view = null;
    RecyclerView recycle_person_public_topic;
    private List<PersonPublicTopicBean> items;
    PersonPublicTopicAdapter personPublicTopicAdapter;
    String user_id;
    @SuppressLint("ValidFragment")
    public Fragment_person_public_topic(PersonPublicActivity personPublicActivity) {
        PersonPublicModel personPublicModel = new PersonPublicModel();
          user_id = personPublicActivity.user_id;
        personPublicModel.getPersonPublicTopicList(user_id,listListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_public_topic,container,false);
        initView();
        initSend();
        return view;
    }



    private void initView() {
        recycle_person_public_topic = view.findViewById(R.id.recycle_person_public_topic);



        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_person_public_topic.setLayoutManager(linearLayoutManager);
        personPublicTopicAdapter = new PersonPublicTopicAdapter(getActivity(),items,R.layout.fragment_person_public_topic_item);
        recycle_person_public_topic.setAdapter(personPublicTopicAdapter);
        recycle_person_public_topic.setItemAnimator(new DefaultItemAnimator());
    }
    private void initSend() {

    }

    ListListener<PersonPublicTopicBean> listListener = new ListListener<PersonPublicTopicBean>() {
        @Override
        public void onResponse(List<PersonPublicTopicBean> list) {
            personPublicTopicAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Log.e("Tag","访问失败了，请检查网络是否连接良好");
        }
    };
}

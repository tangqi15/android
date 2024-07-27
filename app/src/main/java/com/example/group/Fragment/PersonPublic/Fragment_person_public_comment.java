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
import com.example.group.Adapter.PersonPublicCommentAdapter;
import com.example.group.Bean.PersonPublicCommentBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.PersonPublicModel;
import com.example.group.R;

import java.util.List;

@SuppressLint("ValidFragment")
public class Fragment_person_public_comment extends Fragment {
    //发布的评论
    View view = null;
    RecyclerView recycle_person_public_comment;
    private List<PersonPublicCommentBean> items;
    PersonPublicCommentAdapter personPublicCommentAdapter;
    String user_id;
    @SuppressLint("ValidFragment")
    public Fragment_person_public_comment(PersonPublicActivity personPublicActivity) {
        PersonPublicModel personPublicModel = new PersonPublicModel();
        user_id = personPublicActivity.user_id;
        personPublicModel.getPersonPublicCommentList(user_id,listListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_person_public_comment,container,false);
        initView();
        initSend();
        return view;
    }



    private void initView() {
        recycle_person_public_comment = view.findViewById(R.id.recycle_person_public_comment);



        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_person_public_comment.setLayoutManager(linearLayoutManager);
        personPublicCommentAdapter = new PersonPublicCommentAdapter(getActivity(),items,R.layout.fragment_person_public_comment_item);
        recycle_person_public_comment.setAdapter(personPublicCommentAdapter);
        recycle_person_public_comment.setItemAnimator(new DefaultItemAnimator());
    }
    private void initSend() {

    }

    ListListener<PersonPublicCommentBean> listListener = new ListListener<PersonPublicCommentBean>() {
        @Override
        public void onResponse(List<PersonPublicCommentBean> list) {
            personPublicCommentAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Log.e("Tag","访问失败了，请检查网络是否连接良好");
        }
    };
}

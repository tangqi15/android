package com.example.group.Model;


import com.example.group.Bean.ActivityBean;
import com.example.group.Bean.AllGroupBean;
import com.example.group.Bean.GroupStaffBean;
import com.example.group.Bean.TimeBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class GroupModel extends RetrofitBaseModel{

    private OtherServer otherServer;
    private Retrofit retrofit;


    public GroupModel(){
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getGroupList(ListListener<AllGroupBean> listListener){
        Call<List<AllGroupBean>> call = otherServer.getGroup();
        callenqueueList(call,listListener);
    }
    public void getGroupStaffList(String group_id,ListListener<GroupStaffBean> listListener){
        Call<List<GroupStaffBean>> call = otherServer.getGroupStaff(group_id);
        callenqueueList(call,listListener);
    }
    //社团活动

    public void getGroupActivityList(String group_id,ListListener<ActivityBean> listListener){
        Call<List<ActivityBean>> call = otherServer.getGroupActivity(group_id);
        callenqueueList(call,listListener);
    }

    //社团信息

    public void getGroupInfo(String group_id, TListener<AllGroupBean> tListener){
        Call<AllGroupBean> call = otherServer.getGroupInfo(group_id);
        callenqueue(call,tListener);
    }



    //我的社团
    public void getMineGroupList(String user_id, ListListener<AllGroupBean> listListener){
        Call<List<AllGroupBean>> call = otherServer.getMineGroupList(user_id);
        callenqueueList(call,listListener);
    }

    //我收藏的社团
    public void getGroupCollectionList(String user_id, ListListener<AllGroupBean> listListener){
        Call<List<AllGroupBean>> call = otherServer.getGroupCollectionList(user_id);
        callenqueueList(call,listListener);
    }


}

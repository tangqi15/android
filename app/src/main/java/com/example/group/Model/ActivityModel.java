package com.example.group.Model;

import com.example.group.Bean.ActivityBean;
import com.example.group.Listener.ListListener;
import com.example.group.Server.EventServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ActivityModel extends RetrofitBaseModel{

    private EventServer eventServer;
    private Retrofit retrofit;


    public ActivityModel(){
        retrofit = getRetrofit();
        eventServer = retrofit.create(EventServer.class);
    }

    public void getGroupActivity1(ListListener<ActivityBean> listListener){
        Call<List<ActivityBean>> call = eventServer.getGroupActivity1();
        callenqueueList(call,listListener);
    }
    public void getGroupActivity2(ListListener<ActivityBean> listListener){
        Call<List<ActivityBean>> call = eventServer.getGroupActivity2();
        callenqueueList(call,listListener);
    }
    public void getGroupActivity3(ListListener<ActivityBean> listListener){
        Call<List<ActivityBean>> call = eventServer.getGroupActivity3();
        callenqueueList(call,listListener);
    }
    public void getGroupActivity4(ListListener<ActivityBean> listListener){
        Call<List<ActivityBean>> call = eventServer.getGroupActivity4();
        callenqueueList(call,listListener);
    }
    public void getGroupActivity5(ListListener<ActivityBean> listListener){
        Call<List<ActivityBean>> call = eventServer.getGroupActivity5();
        callenqueueList(call,listListener);
    }

    //我的  页面中的  活动
    public void getMineActivityJoin(String user_id,ListListener<ActivityBean> listListener){
        Call<List<ActivityBean>> call = eventServer.getMineActivityJoin(user_id);
        callenqueueList(call,listListener);
    }
    //getMineActivityFinish
    public void getMineActivityFinish(String user_id,ListListener<ActivityBean> listListener){
        Call<List<ActivityBean>> call = eventServer.getMineActivityFinish(user_id);
        callenqueueList(call,listListener);
    }
}

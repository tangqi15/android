package com.example.group.Model;

import com.example.group.Bean.ApplicantsPersonListBean;
import com.example.group.Listener.ListListener;
import com.example.group.Server.EventServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ApplicantsPersonListModel extends RetrofitBaseModel{

    private EventServer eventServer;
    private Retrofit retrofit;


    public ApplicantsPersonListModel(){
        retrofit = getRetrofit();
        eventServer = retrofit.create(EventServer.class);
    }

    public void getApplicantsPersonList(String activity_id,ListListener<ApplicantsPersonListBean> listListener){
        Call<List<ApplicantsPersonListBean>> call = eventServer.getApplicantsPersonList(activity_id);
        callenqueueList(call,listListener);
    }
}

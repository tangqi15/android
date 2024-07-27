package com.example.group.Model;

import com.example.group.Bean.TimeBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;

import retrofit2.Call;
import retrofit2.Retrofit;

public class TimeModel extends RetrofitBaseModel{

    private OtherServer otherServer;
    private Retrofit retrofit;


    public TimeModel(){
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }

    //获取服务器时间
    public void getTime(TListener<TimeBean> timeListener){
        Call<TimeBean> call = otherServer.getTime();
        callenqueue(call,timeListener);
    }
}

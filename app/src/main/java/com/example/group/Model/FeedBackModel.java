package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;

import retrofit2.Call;
import retrofit2.Retrofit;

public class FeedBackModel extends RetrofitBaseModel {

    private OtherServer otherServer;
    private Retrofit retrofit;


    public FeedBackModel() {
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }

        public void getSubmitFeed(String user_id,String feed_back,TListener<SuccessBean> listListener) {
        Call<SuccessBean> call = otherServer.getSubmitFeed(user_id,feed_back);
        callenqueue(call, listListener);
    }
}
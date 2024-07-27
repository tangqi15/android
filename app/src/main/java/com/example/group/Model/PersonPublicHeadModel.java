package com.example.group.Model;

import com.example.group.Bean.UserBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;


import retrofit2.Call;
import retrofit2.Retrofit;

public class PersonPublicHeadModel extends RetrofitBaseModel {

    private OtherServer otherServer;
    private Retrofit retrofit;


    public PersonPublicHeadModel() {
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getPersonPublicHeadMessage(String user_id, TListener<UserBean> listListener) {
        Call<UserBean> call = otherServer.getUserInfo(user_id);
        callenqueue(call, listListener);
    }
}
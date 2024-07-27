package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;

import retrofit2.Call;
import retrofit2.Retrofit;

public class CancelGroupCollectionModel extends RetrofitBaseModel {

    private OtherServer otherServer;
    private Retrofit retrofit;


    public CancelGroupCollectionModel() {
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getCanleCollection(String group_id, String user_id, TListener<SuccessBean> isCollectionCall) {
        Call<SuccessBean> call = otherServer.getCanleCollection(group_id,user_id);
        callenqueue(call, isCollectionCall);
    }
}
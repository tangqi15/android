package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;
import com.example.group.Server.Server;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignApplyModel extends RetrofitBaseModel {
    private OtherServer otherServer;
    private Retrofit retrofit;

    public SignApplyModel(){
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        otherServer = retrofit.create(OtherServer.class);
    }


    public void getSignMessage(String user_id, String activity_id, TListener<SuccessBean> listener) {
        //发送数据
        Call<SuccessBean> call = otherServer.getSignMessage(user_id,activity_id);
        callenqueue(call,listener);
    }



    public void getIsSignMessage(String user_id, String activity_id, TListener<SuccessBean> listener) {
        //发送数据
        Call<SuccessBean> call = otherServer.getIsSignMessage(user_id,activity_id);
        callenqueue(call,listener);
    }
}
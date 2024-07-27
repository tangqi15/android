package com.example.group.Model;


import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;
import com.example.group.Server.Server;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendPicModel extends RetrofitBaseModel {
    private OtherServer otherServer;
    private Retrofit retrofit;

    public SendPicModel(){
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        otherServer = retrofit.create(OtherServer.class);
    }


    public void getPic(String user_id, String head_pic, TListener<SuccessBean> listener) {
        //发送数据
        Call<SuccessBean> call = otherServer.getUpdateHead(user_id,head_pic);
        callenqueue(call,listener);
    }
}

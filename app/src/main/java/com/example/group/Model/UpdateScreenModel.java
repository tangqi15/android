package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;
import com.example.group.Server.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateScreenModel extends RetrofitBaseModel {
    Retrofit retrofit;
    OtherServer otherServer;
    public UpdateScreenModel(){
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getUpdateScreenMessage(String update_screen,String user_id,TListener<SuccessBean> tListener) {
        Call<SuccessBean> call =otherServer.getUpdateScreenMessage(update_screen,user_id);
        callenqueue(call,tListener);
    }
}
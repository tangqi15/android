package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;

import retrofit2.Call;
import retrofit2.Retrofit;

public class QuitGroupModel extends RetrofitBaseModel{

    private OtherServer otherServer;
    private Retrofit retrofit;


    public QuitGroupModel(){
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }
    public void getQuitGroupMessage(String user_id, TListener<SuccessBean> listListener){
        Call<SuccessBean> call = otherServer.getQuitGroupMessage(user_id);
        callenqueue(call,listListener);
    }

}

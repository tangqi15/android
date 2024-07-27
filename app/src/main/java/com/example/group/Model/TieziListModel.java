package com.example.group.Model;



import com.example.group.Bean.FaTieBean;
import com.example.group.Listener.ListListener;
import com.example.group.Server.OtherServer;
import com.example.group.Server.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TieziListModel extends RetrofitBaseModel {
    Retrofit retrofit;
    OtherServer teiziServer;
    public TieziListModel(){
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        teiziServer = retrofit.create(OtherServer.class);
    }

    public void getTieziList(ListListener<FaTieBean> listListener) {
        Call<List<FaTieBean>> call =teiziServer.getTieziList();
        callenqueueList(call,listListener);
    }
}

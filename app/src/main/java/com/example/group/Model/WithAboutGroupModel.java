package com.example.group.Model;

import com.example.group.Bean.AboutGroupListBean;
import com.example.group.Bean.FaTieBean;
import com.example.group.Listener.ListListener;
import com.example.group.Server.OtherServer;
import com.example.group.Server.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WithAboutGroupModel extends RetrofitBaseModel {
    Retrofit retrofit;
    OtherServer otherServer;

    public WithAboutGroupModel(){
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getAboutList(ListListener<AboutGroupListBean> listListener) {
        Call<List<AboutGroupListBean>> call =otherServer.getAboutList();
        callenqueueList(call,listListener);
    }
}

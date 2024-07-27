package com.example.group.Model;

import com.example.group.Bean.AllGroupBean;
import com.example.group.Iface.SearchGroupIface;
import com.example.group.Listener.ListListener;
import com.example.group.Server.OtherServer;
import com.example.group.Server.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchModel extends RetrofitBaseModel implements SearchGroupIface<AllGroupBean> {
    Retrofit retrofit;
    OtherServer otherServer;

    public  SearchModel(){
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        otherServer = retrofit.create(OtherServer.class);
    }


    @Override
    public void getSearchList(String search_name, ListListener<AllGroupBean> listListener) {
        Call<List<AllGroupBean>> call =otherServer.getSearchList(search_name);
        callenqueueList(call,listListener);
    }
}

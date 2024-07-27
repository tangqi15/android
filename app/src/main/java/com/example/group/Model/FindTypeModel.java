package com.example.group.Model;

import com.example.group.Bean.FindTypeBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Server.FindServer;
import com.example.group.Server.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FindTypeModel  extends RetrofitBaseModel {
    private Retrofit retrofit;
    private FindServer findServer;

    public FindTypeModel() {
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        findServer = retrofit.create(FindServer.class);
    }

    public void getFindTypeList(ListListener<FindTypeBean> Listlistener){
        Call<List<FindTypeBean>> call = findServer.getFindTypeList();
        callenqueueList(call, Listlistener);
    }
}

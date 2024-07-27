package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.FindServer;
import com.example.group.Server.Server;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//getFindSubmitMessage
public class FindSubmitModel  extends RetrofitBaseModel {
    private Retrofit retrofit;
    private FindServer findServer;

    public FindSubmitModel() {
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        findServer = retrofit.create(FindServer.class);
    }

    public void getFindSubmitMessage(String user_id, String find_pic,String find_intro,String find_type, TListener<SuccessBean> listener){
        Call<SuccessBean> call = findServer.getFindSubmitMessage(user_id,find_pic,find_intro,find_type);
        callenqueue(call, listener);
    }
}

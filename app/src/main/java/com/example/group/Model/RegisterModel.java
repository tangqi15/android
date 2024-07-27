package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.FindServer;
import com.example.group.Server.OtherServer;

import retrofit2.Call;
import retrofit2.Retrofit;
public class RegisterModel extends RetrofitBaseModel {

    private OtherServer otherServer;
    private Retrofit retrofit;


    public RegisterModel() {
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }


    public void getRegister(String username, String pass1, String screen,String phone, TListener<SuccessBean> tListener) {
        Call<SuccessBean> call = otherServer.getRegister(username,pass1,screen,phone);
        callenqueue(call, tListener);
    }
}
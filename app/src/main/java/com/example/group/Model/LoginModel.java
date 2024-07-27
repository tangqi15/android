package com.example.group.Model;

import com.example.group.Bean.LoginBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;

import retrofit2.Call;
import retrofit2.Retrofit;

public class LoginModel extends RetrofitBaseModel  {
    private OtherServer otherServer;
    private Retrofit retrofit;

    public LoginModel(){
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }





    public void getUserLogMessage(String username, String password, TListener<LoginBean> tListener) {
        //发送
        Call<LoginBean> call= otherServer.getUserLogMessage(username,password);
        //返回
        callenqueue(call,tListener);
    }

}
package com.example.group.Model;

import com.example.group.Bean.UserBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;


import retrofit2.Call;
import retrofit2.Retrofit;

public class MineModel extends RetrofitBaseModel  {
    private OtherServer otherServer;
    private Retrofit retrofit;

    public MineModel(){
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getUserInfo(String user_id, TListener<UserBean> tListener) {
        //发送
        Call<UserBean> call = otherServer.getUserInfo(user_id);
        //返回
        callenqueue(call, tListener);
    }

}

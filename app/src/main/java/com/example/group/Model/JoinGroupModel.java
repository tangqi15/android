package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;


import retrofit2.Call;
import retrofit2.Retrofit;

public class JoinGroupModel extends RetrofitBaseModel {

    private OtherServer otherServer;
    private Retrofit retrofit;


    public JoinGroupModel() {
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getJoinGroupMessage(String user_name,String user_phone,String group_reason,String group_id,String user_id,TListener<SuccessBean> tListener) {
        Call<SuccessBean> call = otherServer.getJoinGroupMessage(user_name,user_phone,group_reason,group_id,user_id);
        callenqueue(call, tListener);
    }
    public void getIsJoin(String group_id,String user_id,TListener<SuccessBean> tListener) {
        Call<SuccessBean> call = otherServer.getIsJoin(group_id,user_id);
        callenqueue(call, tListener);
    }
}
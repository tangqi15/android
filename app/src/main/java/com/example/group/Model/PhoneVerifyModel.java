package com.example.group.Model;

import com.example.group.Bean.PersonPublicTopicBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
//手机号验证 model
public class PhoneVerifyModel extends RetrofitBaseModel {

    private OtherServer otherServer;
    private Retrofit retrofit;


    public PhoneVerifyModel() {
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getVerifyPhoneMessage(String username,String phone, TListener<SuccessBean> tListener) {
        Call<SuccessBean> call = otherServer.getVerifyPhoneMessage(username,phone);
        callenqueue(call, tListener);
    }
}
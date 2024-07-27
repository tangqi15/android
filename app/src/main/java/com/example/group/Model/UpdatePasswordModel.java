package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;
import com.example.group.Server.Server;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class UpdatePasswordModel extends RetrofitBaseModel {
    Retrofit retrofit;
    OtherServer otherServer;
    public UpdatePasswordModel(){
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getUpdatePasswordMessage(String old_pass,String new_pass1, String user_id, TListener<SuccessBean> updatePasswordCallListener) {
        Call<SuccessBean> call =otherServer.getUpdatePasswordMessage(old_pass,new_pass1,user_id);
        callenqueue(call,updatePasswordCallListener);
    }

    public void getNewPasswordMessage(String new_pass1, String username, TListener<SuccessBean> updatePasswordCallListener) {
        Call<SuccessBean> call =otherServer.getNewPasswordMessage(new_pass1,username);
        callenqueue(call,updatePasswordCallListener);
    }
}
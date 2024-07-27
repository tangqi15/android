package com.example.group.Model;

import com.example.group.Bean.SexBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class UserSexModel extends RetrofitBaseModel{

    private OtherServer otherServer;
    private Retrofit retrofit;


    public UserSexModel(){
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }
    public void getPresentSex(String user_id, TListener<SexBean> tListener){
        Call<SexBean> call = otherServer.getPresentSex(user_id);
        callenqueue(call,tListener);
    }
    public void getAllSex(ListListener<SexBean> listListener){
        Call<List<SexBean>> call = otherServer.getAllSex();
        callenqueueList(call,listListener);
    }
    //修改   性别
    public void getUpdateSex(String user_id,String updateLaterSex,TListener<SuccessBean> tListener){
        Call<SuccessBean> call = otherServer.getUpdateSex(user_id,updateLaterSex);
        callenqueue(call,tListener);
    }

}

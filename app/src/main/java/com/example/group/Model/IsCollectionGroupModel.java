package com.example.group.Model;


import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;
import retrofit2.Call;
import retrofit2.Retrofit;

public class IsCollectionGroupModel extends RetrofitBaseModel {

    private OtherServer otherServer;
    private Retrofit retrofit;


    public IsCollectionGroupModel() {
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getIsCollection(String group_id,String user_id,TListener<SuccessBean> isCollectionCall) {
        Call<SuccessBean> call = otherServer.getIsCollection(group_id,user_id);
        callenqueue(call, isCollectionCall);
    }
}
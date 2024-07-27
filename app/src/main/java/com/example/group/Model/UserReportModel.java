package com.example.group.Model;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;
import com.example.group.Server.Server;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserReportModel  extends RetrofitBaseModel {
    private Retrofit retrofit;
    private OtherServer otherServer;

    public UserReportModel() {
        retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        otherServer = retrofit.create(OtherServer.class);
    }

    public void getReportSubmitMessage(String user_id,String user_id_report, String report_pic,String report_content, TListener<SuccessBean> listener){
        Call<SuccessBean> call = otherServer.getReportSubmitMessage(user_id,user_id_report,report_pic,report_content);
        callenqueue(call, listener);
    }
}

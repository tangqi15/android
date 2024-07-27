package com.example.group.Model;

import com.example.group.Bean.FindBean;
import com.example.group.Bean.UserBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Server.FindServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class FindModel extends RetrofitBaseModel{

    private FindServer findServer;
    private Retrofit retrofit;


    public FindModel(){
        retrofit = getRetrofit();
        findServer = retrofit.create(FindServer.class);
    }

    public void getFindAcademic(ListListener<FindBean> listListener){
        Call<List<FindBean>> call = findServer.getFindAcademic();
        callenqueueList(call,listListener);
    }

    public void getFindCulture(ListListener<FindBean> listListener){
        Call<List<FindBean>> call = findServer.getFindCulture();
        callenqueueList(call,listListener);
    }

    public void getFindHumanity(ListListener<FindBean> listListener){
        Call<List<FindBean>> call = findServer.getFindHumanity();
        callenqueueList(call,listListener);
    }

    public void getFindServe(ListListener<FindBean> listListener){
        Call<List<FindBean>> call = findServer.getFindServe();
        callenqueueList(call,listListener);
    }

    public void getFindSports(ListListener<FindBean> listListener){
        Call<List<FindBean>> call = findServer.getFindSports();
        callenqueueList(call,listListener);
    }
    //   发现详情中的     头  （用户  信息 ）
    public void getFindDetailsHead1 (String user_id, TListener<UserBean> tListener1){
        Call<UserBean> call = findServer.getFindDetailsHead1(user_id);
        callenqueue(call,tListener1);
    }
    //   发现详情中的     头  （发现详情  图片 内容 ）
    public void getFindDetailsHead2 (String find_id, TListener<FindBean> tListener2){
        Call<FindBean> call = findServer.getFindDetailsHead2(find_id);
        callenqueue(call,tListener2);
    }
//我的页面中，我的发现
    public void getMineFind (String user_id, ListListener<FindBean> tListe){
        Call<List<FindBean>> call = findServer.getMineFind(user_id);
        callenqueueList(call,tListe);
    }
}

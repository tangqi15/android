package com.example.group.Model;

import com.example.group.Bean.PersonPublicCommentBean;
import com.example.group.Bean.PersonPublicTopicBean;
import com.example.group.Listener.ListListener;
import com.example.group.Server.OtherServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class PersonPublicModel extends RetrofitBaseModel{

    private OtherServer otherServer;
    private Retrofit retrofit;


    public PersonPublicModel(){
        retrofit = getRetrofit();
        otherServer = retrofit.create(OtherServer.class);
    }
    public void getPersonPublicTopicList(String user_id,ListListener<PersonPublicTopicBean> listListener){
        Call<List<PersonPublicTopicBean>> call = otherServer.getPersonPublicTopicList(user_id);
        callenqueueList(call,listListener);
    }
    public void getPersonPublicCommentList(String user_id,ListListener<PersonPublicCommentBean> listListener){
        Call<List<PersonPublicCommentBean>> call = otherServer.getPersonPublicCommentList(user_id);
        callenqueueList(call,listListener);
    }
}

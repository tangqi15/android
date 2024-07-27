package com.example.group.Model;

import com.example.group.Bean.CommentListBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Server.FindServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class PublicCommentModel extends RetrofitBaseModel{

    private FindServer findServer;
    private Retrofit retrofit;


    public PublicCommentModel(){
        retrofit = getRetrofit();
        findServer = retrofit.create(FindServer.class);
    }


    public void getPublicCommentMessage(String find_id, String user_id, String comment_content, TListener<SuccessBean> tListener) {
        Call<SuccessBean> call = findServer.getPublicCommentMessage(find_id,user_id,comment_content);
        callenqueue(call,tListener);
    }
    public void getCommentList(String find_id,ListListener<CommentListBean> listListener){
        Call<List<CommentListBean>> call = findServer.getCommentList(find_id);
        callenqueueList(call,listListener);
    }
}

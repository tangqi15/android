package com.example.group.Model;



import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Server.OtherServer;
import com.example.group.Server.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public  class RetrofitBaseModel {
    OtherServer otherServer;
    Retrofit retrofit;
   /*使用接口
    分为四步，第一步 创建Retrofit对象
             第二步 创建访问API请求
             第三步 发送请求
             第四步 处理结果        */
    //创建 Retrofit 对象
    public Retrofit getRetrofit(){
            retrofit = new Retrofit.Builder().baseUrl(Server.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;
    }

    //创建API请求
    public OtherServer getAllServise(){
         otherServer = retrofit.create(OtherServer.class);
            return otherServer;
    }

    //Retrofit 发送请求
    public <T>void callenqueue(
            Call<T> call, final TListener<T> tListener){
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    //有响应处理
                    tListener.onResponse(response.body());
                    //如果数据成功拿走
                }
                else {
                    tListener.onFail("error1");
                }
            }
            //失败处理
            @Override
            public void onFailure(Call<T> call, Throwable t) {
                tListener.onFail("error2");
            }
        });
    }



    public <T>void callenqueueList(Call<List<T>> call,
                                   final ListListener<T> listListener){
        call.enqueue(new Callback<List<T>>() {
            @Override
            public void onResponse(Call<List<T>> call, Response<List<T>> response) {
                if (response.isSuccessful()&&response.body()!=null){
                    listListener.onResponse(response.body());//如果数据成功拿走
                }
                else {
                    listListener.onFail("error shop1");
                }
            }

            @Override
            public void onFailure(Call<List<T>> call, Throwable t) {
                listListener.onFail("error shop2");
            }
        });
    }
    }


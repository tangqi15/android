package com.example.group.Server;

import com.example.group.Bean.ActivityBean;
import com.example.group.Bean.ApplicantsPersonListBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventServer {
    //本社团成员
    //http://localhost/neu_soft_group/public/index.php/api/api/getGroupActivity1
    @GET("neu_soft_group/public/index.php/api/api/getGroupActivity1")
    public Call<List<ActivityBean>> getGroupActivity1();
  //文化类
    @GET("neu_soft_group/public/index.php/api/api/getGroupActivity2")
    public Call<List<ActivityBean>> getGroupActivity2();
    //体育类
    @GET("neu_soft_group/public/index.php/api/api/getGroupActivity3")
    public Call<List<ActivityBean>> getGroupActivity3();

    //人文类
    @GET("neu_soft_group/public/index.php/api/api/getGroupActivity4")
    public Call<List<ActivityBean>> getGroupActivity4();

    //服务类
    @GET("neu_soft_group/public/index.php/api/api/getGroupActivity5")
    public Call<List<ActivityBean>> getGroupActivity5();


    //getApplicantsPersonList  已报名列表
    //http://localhost/neu_soft_group/public/index.php/api/api/getApplicantsPersonList?activity_id=1
    @GET("neu_soft_group/public/index.php/api/api/getApplicantsPersonList")
    public Call<List<ApplicantsPersonListBean>> getApplicantsPersonList(
                                        @Query("activity_id") String activity_id);

    //正在参与的活动
    @GET("neu_soft_group/public/index.php/api/api/getMineActivityJoin")
    public Call<List<ActivityBean>> getMineActivityJoin(@Query("user_id") String user_id);

  //已经结束的活动
  @GET("neu_soft_group/public/index.php/api/api/getMineActivityFinish")
  public Call<List<ActivityBean>> getMineActivityFinish(@Query("user_id") String user_id);







}

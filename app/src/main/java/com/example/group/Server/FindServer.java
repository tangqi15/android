package com.example.group.Server;

import com.example.group.Bean.CommentListBean;
import com.example.group.Bean.FindBean;
import com.example.group.Bean.FindTypeBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Bean.UserBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FindServer {
    //getFindAcademic
    //学术科技类
    @GET("neu_soft_group/public/index.php/api/api/getFindAcademic")
    public Call<List<FindBean>> getFindAcademic();
    //文化艺术类
    @GET("neu_soft_group/public/index.php/api/api/getFindCulture")
    public Call<List<FindBean>> getFindCulture();
    //体育类
    @GET("neu_soft_group/public/index.php/api/api/getFindSports")
    public Call<List<FindBean>> getFindSports();

    //人文类
    @GET("neu_soft_group/public/index.php/api/api/getFindHumanity")
    public Call<List<FindBean>> getFindHumanity();

    //服务类
    @GET("neu_soft_group/public/index.php/api/api/getFindServe")
    public Call<List<FindBean>> getFindServe();

    //获取评论列表

    @GET("neu_soft_group/public/index.php/api/api/getCommentList")
    public Call<List<CommentListBean>> getCommentList(@Query("find_id") String find_id
                                                        );
    //发布评论
    @GET("neu_soft_group/public/index.php/api/api/getPublicCommentMessage")
    public Call<SuccessBean> getPublicCommentMessage(@Query("find_id") String find_id,
                                                     @Query("user_id") String user_id,
                                                     @Query("comment_content") String comment_content
                                                    );


    //发现详情  页面
    @GET("neu_soft_group/public/index.php/api/api/getFindDetailsHead1")
    public Call<UserBean> getFindDetailsHead1(@Query("user_id") String user_id
    );

    //发现详情  页面
    @GET("neu_soft_group/public/index.php/api/api/getFindDetailsHead2")
    public Call<FindBean> getFindDetailsHead2(@Query("find_id") String find_id
    );

    // getFindTypeList   条目类型列表
    @GET("neu_soft_group/public/index.php/api/api/getFindTypeList")
    public Call<List<FindTypeBean>> getFindTypeList();

   // getFindSubmitMessage   发布  发现条目   user_id,find_pic,find_intro
   @GET("neu_soft_group/public/index.php/api/api/getFindSubmitMessage")
   public Call<SuccessBean> getFindSubmitMessage(@Query("user_id") String user_id,
                                              @Query("find_pic") String find_pic,
                                              @Query("find_intro") String find_intro,
                                              @Query("find_type") String find_type
   );


    //我的页面中  我的发现
    @GET("neu_soft_group/public/index.php/api/api/getMineFind")
    public Call<List<FindBean>> getMineFind(@Query("user_id") String user_id);


}

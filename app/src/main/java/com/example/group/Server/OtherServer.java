package com.example.group.Server;

import com.example.group.Bean.AboutGroupListBean;
import com.example.group.Bean.ActivityBean;
import com.example.group.Bean.AllGroupBean;
import com.example.group.Bean.FaTieBean;
import com.example.group.Bean.GroupStaffBean;
import com.example.group.Bean.LoginBean;
import com.example.group.Bean.PersonPublicCommentBean;
import com.example.group.Bean.PersonPublicTopicBean;
import com.example.group.Bean.SexBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Bean.TimeBean;
import com.example.group.Bean.UserBean;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OtherServer {
    //登录

    @GET("neu_soft_group/public/index.php/api/api/getUserLogMessage")
    public Call<LoginBean> getUserLogMessage(@Query("username") String username,
                                   @Query("password") String password);

    //http://localhost/neu_soft_group/public/index.php/api/api/getGroup
    //http://10.0.2.2/neusoft_group/public/index.php/api/api/getGroup
    @GET("neu_soft_group/public/index.php/api/api/getGroup")
    public Call<List<AllGroupBean>> getGroup();


    //本社团成员
    //http://localhost/neu_soft_group/public/index.php/api/api/getGroupStaff
    @GET("neu_soft_group/public/index.php/api/api/getGroupStaff")
    public Call<List<GroupStaffBean>> getGroupStaff(@Query("group_id") String group_id);
   //本社团活动
    //http://localhost/neu_soft_group/public/index.php/api/api/getGroupActivity
    @GET("neu_soft_group/public/index.php/api/api/getGroupActivity")
    public Call<List<ActivityBean>> getGroupActivity(@Query("group_id") String group_id);

    //社团信息
    //http://localhost/neu_soft_group/public/index.php/api/api/getGroupInfo
    @GET("neu_soft_group/public/index.php/api/api/getGroupInfo")
    public Call<AllGroupBean> getGroupInfo(@Query("group_id") String group_id);

 //服务器时间
    //    http://localhost/neu_soft_group/public/index.php/api/api/getTime
 //       http://localhost/neu_soft_group/public/index.php/api/api/getTime
 @GET("neu_soft_group/public/index.php/api/api/getTime")
 public Call<TimeBean> getTime();



    //测验
    @GET("appropriate/public/index.php/api/api/getPostMessageList")
    public Call<List<FaTieBean>> getTieziList();


    //获取个人发布的话题
    //http://localhost/neu_soft_group/public/index.php/api/api/getPersonPublicTopicList?user_id=1
    @GET("neu_soft_group/public/index.php/api/api/getPersonPublicTopicList")
    public Call<List<PersonPublicTopicBean>> getPersonPublicTopicList(@Query("user_id") String user_id);


    //获取个人发布的评论
    //http://localhost/neu_soft_group/public/index.php/api/api/getPersonPublicTopicList?user_id=1
    @GET("neu_soft_group/public/index.php/api/api/getPersonPublicCommentList")
    public Call<List<PersonPublicCommentBean>> getPersonPublicCommentList(@Query("user_id") String user_id);

    //获取关于我们
    //http://localhost/neu_soft_group/public/index.php/api/api/getAboutList
    @GET("neu_soft_group/public/index.php/api/api/getAboutList")
    public Call<List<AboutGroupListBean>> getAboutList();

    //获取个人信息
 //   http://localhost/neu_soft_group/public/index.php/api/api/getUserInfo?user_id=2
    @GET("neu_soft_group/public/index.php/api/api/getUserInfo")
    public Call<UserBean> getUserInfo(@Query("user_id") String user_id);

 //修改用户头像
    // neu_soft_group/public/index.php/api/api/getUpdateHead?user_id=2&user_pic=4.jpg


    @GET("neu_soft_group/public/index.php/api/api/getUpdateHead")
    public Call<SuccessBean> getUpdateHead(@Query("user_id")String user_id,
                                           @Query("user_pic")String user_pic);

    //  getJoinGroupMessage  申请加入社团
    @GET("neu_soft_group/public/index.php/api/api/getJoinGroupMessage")
    public Call<SuccessBean> getJoinGroupMessage(@Query("user_name")String user_name,
                                           @Query("user_phone")String user_phone,
                                           @Query("group_reason")String group_reason,
                                           @Query("group_id")String group_id,
                                           @Query("user_id")String user_id);



    //  getJoinGroupMessage  是否  已经申请过了
    @GET("neu_soft_group/public/index.php/api/api/getIsJoin")
    public Call<SuccessBean> getIsJoin(@Query("group_id")String group_id,
                                       @Query("user_id")String user_id);

    //getSignMessage
    //报名活动
    @GET("neu_soft_group/public/index.php/api/api/getSignMessage")
    public Call<SuccessBean> getSignMessage(@Query("user_id")String user_id,
                                            @Query("activity_id")String activity_id);

    //getSignMessage
    //报名活动   查看是否报名
    @GET("neu_soft_group/public/index.php/api/api/getIsSignMessage")
    public Call<SuccessBean> getIsSignMessage(@Query("user_id")String user_id,
                                            @Query("activity_id")String activity_id);


    //getMineGroupList
    //  获取我的页面的       我的社团
//    http://localhost/neu_soft_group/public/index.php/api/api/getMineGroupList?user_id=2
    @GET("neu_soft_group/public/index.php/api/api/getMineGroupList")
    public Call<List<AllGroupBean>> getMineGroupList(@Query("user_id")String user_id);

 //getMineGroupList
 //  获取我的页面的       我收藏的社团
//http://localhost/neu_soft_group/public/index.php/api/api/getMineGroupList?user_id=2
 @GET("neu_soft_group/public/index.php/api/api/getGroupCollectionList")
 public Call<List<AllGroupBean>> getGroupCollectionList(@Query("user_id")String user_id);


    //getUpdateScreenMessage
    //  获取我的页面的       修改昵称
//http://localhost/neu_soft_group/public/index.php/api/api/getMineGroupList?user_id=2
    @GET("neu_soft_group/public/index.php/api/api/getUpdateScreenMessage")
    public Call<SuccessBean> getUpdateScreenMessage(@Query("update_screen") String update_screen,
                                                    @Query("user_id")String user_id);

//  我的页面中 个人信息    性别列表   getAllSex
//http://localhost/neu_soft_group/public/index.php/api/api/getAllSex
 @GET("neu_soft_group/public/index.php/api/api/getAllSex")
 public Call<List<SexBean>> getAllSex();

 //我的页面中  个人信息    当前性别     getPresentSex

 //http://localhost/neu_soft_group/public/index.php/api/api/getPresentSex?user_id=1
 @GET("neu_soft_group/public/index.php/api/api/getPresentSex")
 public Call<SexBean> getPresentSex(@Query("user_id")String user_id);


 //对性别   进行修改
 //http://localhost/neu_soft_group/public/index.php/api/api/getUpdateSex?user_id=1
 @GET("neu_soft_group/public/index.php/api/api/getUpdateSex")
 public Call<SuccessBean> getUpdateSex(@Query("user_id")String user_id,
                                       @Query("updateLaterSex")String updateLaterSex);

//对  密码进行 修改


 //http://localhost/neu_soft_group/public/index.php/api/api/getUpdateSex?user_id=1
 //old_pass,new_pass1,user_id
 @GET("neu_soft_group/public/index.php/api/api/getUpdatePasswordMessage")
 public Call<SuccessBean> getUpdatePasswordMessage(@Query("old_pass")String old_pass,
                                       @Query("new_pass1")String new_pass1,
                                       @Query("user_id")String user_id);
//对  密码进行 修改      忘记密码中   设置新密码
 //http://localhost/neu_soft_group/public/index.php/api/api/getNewPasswordMessage?new_pass1=123&username=zhangzhang
 //old_pass,new_pass1,user_id
 @GET("neu_soft_group/public/index.php/api/api/getNewPasswordMessage")
 public Call<SuccessBean> getNewPasswordMessage(@Query("new_pass1")String new_pass1,
                                                @Query("username")String username);

//搜索   getSearchList
 //getInsertActivity?search_name=1
 @GET("neu_soft_group/public/index.php/api/api/getSearchList")
 public Call<List<AllGroupBean>> getSearchList(@Query("search_name") String search_name);

//社团是否收藏  getIsCollection   group_id,user_id
@GET("neu_soft_group/public/index.php/api/api/getIsCollection")
public Call<SuccessBean> getIsCollection(@Query("group_id") String group_id,
                                         @Query("user_id") String user_id);

 //社团添加收藏  getIsCollection   group_id,user_id
 @GET("neu_soft_group/public/index.php/api/api/getAddCollection")
 public Call<SuccessBean> getAddCollection(@Query("group_id") String group_id,
                                          @Query("user_id") String user_id);

 //社团取消收藏  getIsCollection   group_id,user_id
 @GET("neu_soft_group/public/index.php/api/api/getCanleCollection")
 public Call<SuccessBean> getCanleCollection(@Query("group_id") String group_id,
                                          @Query("user_id") String user_id);


//注册  getRegister   username,pass1,screen,phone
 @GET("neu_soft_group/public/index.php/api/api/getRegister")
 public Call<SuccessBean> getRegister(@Query("username") String username,
                                             @Query("pass1") String pass1,
                                             @Query("screen") String screen,
                                             @Query("phone") String phone
                                      );

 //手机号  是否在数据库中 存在
 // getVerifyPhoneMessage
 @GET("neu_soft_group/public/index.php/api/api/getVerifyPhoneMessage")
 public Call<SuccessBean> getVerifyPhoneMessage(@Query("username") String username,
                                      @Query("phone") String phone
 );

 //发布举报
 @GET("neu_soft_group/public/index.php/api/api/getReportSubmitMessage")
 public Call<SuccessBean> getReportSubmitMessage(@Query("user_id") String user_id,
                                                 @Query("user_id_report") String user_id_report,
                                                  @Query("report_pic") String report_pic,
                                                  @Query("report_content") String report_content
 );

 //退出社团  getQuitGroupMessage   user_id
 @GET("neu_soft_group/public/index.php/api/api/getQuitGroupMessage")
 public Call<SuccessBean> getQuitGroupMessage(@Query("user_id") String user_id);

 //提交反馈  getSubmitFeed   user_id
 @GET("neu_soft_group/public/index.php/api/api/getSubmitFeed")
 public Call<SuccessBean> getSubmitFeed(@Query("user_id") String user_id,
                                        @Query("feed_back") String feed_back);
}

package com.example.group.Bean;

public class ApplicantsPersonListBean {
//"activity_order_id":5,"activity_order_time":"2019-03-04 11:23:43",
// "user_id":1,"activity_id":1,"screen":"网络少年",
// "user_pic":"1.jpg","check":1,"username":"tangqi","password":"202cb962ac59075b964b07152d234b70",
// "join_time":"2018-12-17 18:31:00","count_down_time":"0000-00-00 00:00:00",
// "group_id":1,"sex":1,"group_name":"大连东软信息学院羽毛球协会",
// "group_pic":"yumaoqiu.jpg",
// "group_intro":"大连东软信息学院是直属东软信息学院后勤部，
//                  羽毛球协会是全校羽毛球爱好者的社团。本协
//                      会本着为同学们服务的精神，在协同团委
//                  学生会体育部组织开展各级赛事，通过各种训练
//                  和比赛提高同学们羽毛球水平，从而达到强身健体
//          的目的。
// ","group_type_id":3,"collect":"12"}},
    private String activity_order_id;
    private String activity_order_time;
    private String user_id;
    private String activity_id;
    private String screen;
    private String user_pic;
    private String group_name;

    public String getActivity_order_id() {
        return activity_order_id;
    }

    public void setActivity_order_id(String activity_order_id) {
        this.activity_order_id = activity_order_id;
    }

    public String getActivity_order_time() {
        return activity_order_time;
    }

    public void setActivity_order_time(String activity_order_time) {
        this.activity_order_time = activity_order_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}

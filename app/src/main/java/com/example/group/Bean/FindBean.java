package com.example.group.Bean;

public class FindBean {
    //[{"find_id":1,"user_id":1,"find_intro":"好嗨哟",
    // "find_addtime":"2019-02-13 20:37:10",
    // "find_type_id":1,"find_pic":"",
    // "screen":"网络少年","user_pic":"1.jqg",
    // "check":1,"username":"tangqi",
    // "password":"202cb962ac59075b964b07152d234b70"
    // ,"join_time":"2018-12-17 18:31:00",
    // "count_down_time":"0000-00-00 00:00:00",
    // "group_id":1,"find_type_name":"学术科技类",
    // "find_type_flag":1}]
    private String find_id;
    private String user_id;
    private String find_intro;
    private String find_addtime;
    private String find_pic;
    private String screen;
    private String user_pic;
    private String comment_sum;

    public String getFind_id() {
        return find_id;
    }

    public void setFind_id(String find_id) {
        this.find_id = find_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFind_intro() {
        return find_intro;
    }

    public void setFind_intro(String find_intro) {
        this.find_intro = find_intro;
    }

    public String getFind_addtime() {
        return find_addtime;
    }

    public void setFind_addtime(String find_addtime) {
        this.find_addtime = find_addtime;
    }

    public String getFind_pic() {
        return find_pic;
    }

    public void setFind_pic(String find_pic) {
        this.find_pic = find_pic;
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

    public String getComment_sum() {
        return comment_sum;
    }

    public void setComment_sum(String comment_sum) {
        this.comment_sum = comment_sum;
    }
}

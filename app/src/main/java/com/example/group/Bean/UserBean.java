package com.example.group.Bean;

public class UserBean {
    //[{"user_id":1,"screen":"网络少年"
    // ,"user_pic":"1.jpg","check":1,
    // "username":"tangqi","password":"202cb962ac59075b964b07152d234b70"
    // ,"join_time":"2018-12-17 18:31:00",
    // "count_down_time":"0000-00-00 00:00:00","group_id":1
    // ""sex":1,"sex_type_id":1,"sex_name":"男"}]
    private String user_id;
    private String screen;
    private String user_pic;
    private String check;
    private String username;
    private String join_time;
    private String count_down_time;
    private String group_id;
    private String sex;
    private String sex_type_id;
    private String sex_name;
    private String phone;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJoin_time() {
        return join_time;
    }

    public void setJoin_time(String join_time) {
        this.join_time = join_time;
    }

    public String getCount_down_time() {
        return count_down_time;
    }

    public void setCount_down_time(String count_down_time) {
        this.count_down_time = count_down_time;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex_type_id() {
        return sex_type_id;
    }

    public void setSex_type_id(String sex_type_id) {
        this.sex_type_id = sex_type_id;
    }

    public String getSex_name() {
        return sex_name;
    }

    public void setSex_name(String sex_name) {
        this.sex_name = sex_name;
    }
}

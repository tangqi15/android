package com.example.group.Bean;

public class FaTieBean {
      //  "post_message_id":1,
    // "user_id":2,
    // "post_message_title":"组团10月13号登山请联系我",
    // "post_message_content":"组队登山，请联系我。手机号13452103654"}
    //"screen":"青春王子","user_pic":"1.jqg","check":1,
      // "username":"songjian","password":"202cb962ac59075b964b07152d234b70",
      // "join_time":"2018-12-17 18:31:00",
      // "count_down_time":"0000-00-00 00:00:00"}
    private String post_message_id;
    private String user_id;
    private String post_message_title;
    private String post_message_content;
    private String screen;
    private String user_pic;
    private String post_time;

    public String getPost_message_id() {
        return post_message_id;
    }

    public void setPost_message_id(String post_message_id) {
        this.post_message_id = post_message_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_message_title() {
        return post_message_title;
    }

    public void setPost_message_title(String post_message_title) {
        this.post_message_title = post_message_title;
    }

    public String getPost_message_content() {
        return post_message_content;
    }

    public void setPost_message_content(String post_message_content) {
        this.post_message_content = post_message_content;
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

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }
}

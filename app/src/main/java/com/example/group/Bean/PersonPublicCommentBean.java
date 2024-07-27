package com.example.group.Bean;

public class PersonPublicCommentBean {
    //"find_comment_id":1,"find_id":1,
    // "find_comment_content":"嘿嘿",
    // "find_comment_time":"2019-02-15 19:31:58","user_id":1}
    private String find_comment_id;
    private String find_id;
    private String find_comment_content;
    private String find_comment_time;
    private String user_id;

    public String getFind_comment_id() {
        return find_comment_id;
    }

    public void setFind_comment_id(String find_comment_id) {
        this.find_comment_id = find_comment_id;
    }

    public String getFind_id() {
        return find_id;
    }

    public void setFind_id(String find_id) {
        this.find_id = find_id;
    }

    public String getFind_comment_content() {
        return find_comment_content;
    }

    public void setFind_comment_content(String find_comment_content) {
        this.find_comment_content = find_comment_content;
    }

    public String getFind_comment_time() {
        return find_comment_time;
    }

    public void setFind_comment_time(String find_comment_time) {
        this.find_comment_time = find_comment_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}

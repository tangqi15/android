package com.example.group.Bean;

public class GroupStaffBean {
   // group_staff_id":1,"group_id":1,
    // "group_post_id":1,"group_staff_name":"张亮"
    // ,"group_staff_pic":"1.jpg"
    // ,"group_staff_intro":"为社团服务是我的意向"
   // group_post_type_id":1,"group_post_type_name":"社长}]
    private String group_staff_id;
    private String group_id;
    private String group_post_id;
    private String group_staff_name;
    private String group_staff_pic;
    private String group_staff_intro;
    private String group_post_type_id;
    private String group_post_type_name;

    public String getGroup_post_type_id() {
        return group_post_type_id;
    }

    public void setGroup_post_type_id(String group_post_type_id) {
        this.group_post_type_id = group_post_type_id;
    }

    public String getGroup_post_type_name() {
        return group_post_type_name;
    }

    public void setGroup_post_type_name(String group_post_type_name) {
        this.group_post_type_name = group_post_type_name;
    }

    public String getGroup_staff_id() {
        return group_staff_id;
    }

    public void setGroup_staff_id(String group_staff_id) {
        this.group_staff_id = group_staff_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_post_id() {
        return group_post_id;
    }

    public void setGroup_post_id(String group_post_id) {
        this.group_post_id = group_post_id;
    }

    public String getGroup_staff_name() {
        return group_staff_name;
    }

    public void setGroup_staff_name(String group_staff_name) {
        this.group_staff_name = group_staff_name;
    }

    public String getGroup_staff_pic() {
        return group_staff_pic;
    }

    public void setGroup_staff_pic(String group_staff_pic) {
        this.group_staff_pic = group_staff_pic;
    }

    public String getGroup_staff_intro() {
        return group_staff_intro;
    }

    public void setGroup_staff_intro(String group_staff_intro) {
        this.group_staff_intro = group_staff_intro;
    }
}

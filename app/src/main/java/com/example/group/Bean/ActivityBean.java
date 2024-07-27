package com.example.group.Bean;

public class ActivityBean {
    // [{"activity_id":1,"activity_name":"十佳歌手",
    // "activity_intro":"欢迎同学们来展示自己的歌喉"
    // "activity_start_time":"2019-01-16 18:00:00",
    // "actviity_finish_time":"2019-01-16 21:00:00",
    // "activity_pic":"1.jpg","group_id":1,
    // "activity_address":"A3-206"}]
    private String activity_id;
    private String activity_name;
    private String activity_intro;
    private String activity_start_time;
    private String actviity_finish_time;
    private String activity_pic;
    private String group_id;
    private String activity_address;
    private String activity_is_apply;

    public String getActivity_address() {
        return activity_address;
    }

    public void setActivity_address(String activity_address) {
        this.activity_address = activity_address;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_intro() {
        return activity_intro;
    }

    public void setActivity_intro(String activity_intro) {
        this.activity_intro = activity_intro;
    }

    public String getActivity_start_time() {
        return activity_start_time;
    }

    public void setActivity_start_time(String activity_start_time) {
        this.activity_start_time = activity_start_time;
    }

    public String getActviity_finish_time() {
        return actviity_finish_time;
    }

    public void setActviity_finish_time(String actviity_finish_time) {
        this.actviity_finish_time = actviity_finish_time;
    }

    public String getActivity_pic() {
        return activity_pic;
    }

    public void setActivity_pic(String activity_pic) {
        this.activity_pic = activity_pic;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getActivity_is_apply() {
        return activity_is_apply;
    }

    public void setActivity_is_apply(String activity_is_apply) {
        this.activity_is_apply = activity_is_apply;
    }
}

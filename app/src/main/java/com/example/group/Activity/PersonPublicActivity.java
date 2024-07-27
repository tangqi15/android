package com.example.group.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.group.Bean.UserBean;
import com.example.group.Fragment.PersonPublic.Fragment_person_public_comment;
import com.example.group.Fragment.PersonPublic.Fragment_person_public_topic;
import com.example.group.Listener.TListener;
import com.example.group.Model.PersonPublicHeadModel;
import com.example.group.R;
import com.example.group.Server.Server;
import com.example.group.util.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;



public class PersonPublicActivity extends BaseActivity {
//个人发布页面

    //控件
    ImageView img_person_public_back;
    ImageView img_screen_head;
    TextView tv_screen_name;
    TextView tv_sex;
    LinearLayout ly_report;
    private Fragment_person_public_topic fragment_person_public_topic =null;
    private Fragment_person_public_comment fragment_person_public_comment=null;
    public String user_id;
    //当前屏幕的密度
    private DisplayMetrics dm;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_public);
        InitView();
        InitEvent();
        InitHead();
        InitBind();
    }


    private void InitView() {
        img_person_public_back = findViewById(R.id.img_person_public_back);
        img_screen_head = findViewById(R.id.img_screen_head);
        tv_screen_name = findViewById(R.id.tv_screen_name);
        tv_sex = findViewById(R.id.tv_sex);
        ly_report = findViewById(R.id.ly_report);
        //上个页面传过来的用户ID
        user_id = getIntent().getStringExtra("user_id");
        //轻量级存储取出来登录者的ID
        String  uid = getuid();
        if (user_id.equals(uid)||user_id.equals("1")){
            //设置空间隐藏
            //  ly_report.setVisibility(View.GONE);                     处于隐藏状态  不显示，不占用内存
            //    ly_report.setVisibility(View.INVISIBLE);              处于隐藏状态  不显示，仍占有内存
            //    ly_report.setVisibility(View.VISIBLE);                处于显示状态
            ly_report.setVisibility(View.GONE);

        }
    }
    private void InitHead() {

        PersonPublicHeadModel personPublicHeadModel = new PersonPublicHeadModel();
        personPublicHeadModel.getPersonPublicHeadMessage(user_id,PersonPublicHeadMessage);
    }
TListener<UserBean> PersonPublicHeadMessage = new TListener<UserBean>() {
    @Override
    public void onResponse(UserBean userBean) {
        String screen_name = userBean.getScreen();
        String sex_name = userBean.getSex_name();
        String user_pic = userBean.getUser_pic();
        tv_screen_name.setText(screen_name);
        tv_sex.setText(sex_name);
        Picasso.with(PersonPublicActivity.this).
                load(Server.BASE_URL+"neu_soft_group/public/static/user_head/" + user_pic).
                into(img_screen_head);
    }

    @Override
    public void onFail(String msg) {

    }
};
    private void InitEvent() {
        img_person_public_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ly_report.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(PersonPublicActivity.this,UserReportActivity.class);
            //穿被举报人的id
            intent.putExtra("user_id",user_id);
            startActivity(intent);
            }
        });
    }
    private void InitBind() {
        //获取屏幕密度
        dm = getResources().getDisplayMetrics();

        viewPager = (ViewPager)findViewById(R.id.person_public_viewPager);
      /*
      需要管理相互独立的并且隶属于 Activity 的Fragment使用 FragmentManager(),
      而在Fragment中动态添加Fragment要使用getChildFragmentManager()来管理。
       */
        //getSupportFragmentManager
        viewPager.setAdapter(new PersonPublicActivity.MyPagerAdapter(getSupportFragmentManager()));
        pagerSlidingTabStrip = (PagerSlidingTabStrip)findViewById(R.id.person_public_pagerslidingtabstrip);
        pagerSlidingTabStrip.setViewPager(viewPager);
        setPagerStyle();//设置PagerSlidingTabStrip显示效果
    }
    private void setPagerStyle() {
        // 设置Tab是自动填充满屏幕的
        pagerSlidingTabStrip.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        pagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
        // 设置Tab底部线的高度
        pagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 1, dm));
        // 设置Tab Indicator的高度
        pagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小
        pagerSlidingTabStrip.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        pagerSlidingTabStrip.setIndicatorColor(Color.parseColor("#45c01a"));
        // 设置选中Tab文字的颜色 (这是自定义的一个方法)
        pagerSlidingTabStrip.setSelectedTextColor(Color.parseColor("#45c01a"));
        // 取消点击Tab时的背景色
        pagerSlidingTabStrip.setTabBackground(0);
    }

    //自定义ViewPagerAdapter子类
    private class MyPagerAdapter extends FragmentPagerAdapter {
        private String[] titles={"ta的话题","ta的评论"};//显示在二级导航上的标题文字
        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];//确定选择当页导航上文字
        }
        @Override
        public int getCount() {
            return titles.length;//二级导航个数
        }

        @Override
        public Fragment getItem(int position) {
            //根据位置返回具体某个导航上对应的Fragment
            switch (position)
            {
                case 0:
                    if(fragment_person_public_topic==null)
                    {
                        fragment_person_public_topic=new Fragment_person_public_topic(PersonPublicActivity.this);
                    }
                    return fragment_person_public_topic;
                case 1:
                    if(fragment_person_public_comment ==null)
                    {
                        fragment_person_public_comment =new Fragment_person_public_comment(PersonPublicActivity.this);
                    }
                    return fragment_person_public_comment;
                default:
                    return null;
            }
        }
    }
    private String getuid() {
        SharedPreferences sp =getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id", "");
    }
}

package com.example.group.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.example.group.Fragment.Fragment_Mine_Activity.Fragment_mine_activity_finish;
import com.example.group.Fragment.Fragment_Mine_Activity.Fragment_mine_activity_participation;
import com.example.group.R;
import com.example.group.util.PagerSlidingTabStrip;

public class MineActivityActivity extends BaseActivity {
ImageView img_ming_activity_back;
    //当前屏幕的密度
    private DisplayMetrics dm;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
    //正在参与的  活动
    Fragment_mine_activity_participation fragment_mine_activity_participation = null;
    //  已经结束的   活动
    Fragment_mine_activity_finish fragment_mine_activity_finish = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_activity);
        initView();
        initEvent();
        initBind();
    }

    private void initView() {
        img_ming_activity_back = findViewById(R.id.img_ming_activity_back);
    }
    private void initEvent() {
        img_ming_activity_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initBind() {
        //获取屏幕密度
        dm = getResources().getDisplayMetrics();
        viewPager = (ViewPager)findViewById(R.id.mine_activity_viewPager);
      /*
      需要管理相互独立的并且隶属于 Activity 的Fragment使用 FragmentManager(),
      而在Fragment中动态添加Fragment要使用getChildFragmentManager()来管理。
       */
        //getSupportFragmentManager
        viewPager.setAdapter(new MineActivityActivity.MyPagerAdapter(getSupportFragmentManager()));

        pagerSlidingTabStrip = (PagerSlidingTabStrip)findViewById(R.id.mine_activity_PagerSlidingTabStrip);
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
        private String[] titles={"正在参与","已经结束"};//显示在二级导航上的标题文字
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
                    if(fragment_mine_activity_participation==null)
                    {
                        fragment_mine_activity_participation=new Fragment_mine_activity_participation();
                    }
                    return fragment_mine_activity_participation;
                case 1:
                    if(fragment_mine_activity_finish ==null)
                    {
                        fragment_mine_activity_finish =new Fragment_mine_activity_finish();
                    }
                    return fragment_mine_activity_finish;
                default:
                    return null;
            }
        }
    }
}

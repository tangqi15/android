package com.example.group.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.group.Fragment.Fragment_Event.FragmentEvent_academic;
import com.example.group.Fragment.Fragment_Event.FragmentEvent_culture;
import com.example.group.Fragment.Fragment_Event.FragmentEvent_humanity;
import com.example.group.Fragment.Fragment_Event.FragmentEvent_serve;
import com.example.group.Fragment.Fragment_Event.FragmentEvent_spots;
import com.example.group.R;
import com.example.group.util.PagerSlidingTabStrip;

public class FragmentEvent extends Fragment {
    //  App上  指的是   活动
    //事件      活动    比赛
    View view = null;
    //学术科技
    private FragmentEvent_academic fragmentEvent_academic=null;
    //文化艺术
    private FragmentEvent_culture fragmentEvent_culture=null;
    //体育竞技
    private FragmentEvent_spots fragmentEvent_spots=null;
    //人文政治
    private FragmentEvent_humanity fragmentEvent_humanity=null;
    //服务实践
    private FragmentEvent_serve fragmentEvent_serve=null;
    //当前屏幕的密度
    private DisplayMetrics dm;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.event_fragment,container,false);
        initView();
        return view;
    }

    private void initView() {
        //获取屏幕密度
        dm = getResources().getDisplayMetrics();
        viewPager = (ViewPager)view.findViewById(R.id.viewPager);
      /*
      需要管理相互独立的并且隶属于 Activity 的Fragment使用 FragmentManager(),
      而在Fragment中动态添加Fragment要使用getChildFragmentManager()来管理。
       */
        viewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));

        pagerSlidingTabStrip = (PagerSlidingTabStrip)view.findViewById(R.id.pagerslidingtabstrip);
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
        private String[] titles={"学术科技","文化艺术","体育竞技","人文政治","服务实践"};//显示在二级导航上的标题文字
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
                    if(fragmentEvent_academic==null)
                    {
                        fragmentEvent_academic=new FragmentEvent_academic();
                    }
                    return fragmentEvent_academic;
                case 1:
                    if(fragmentEvent_culture==null)
                    {
                        fragmentEvent_culture=new FragmentEvent_culture();
                    }
                    return fragmentEvent_culture;
                case 2:
                    if(fragmentEvent_spots==null)
                    {
                        fragmentEvent_spots=new FragmentEvent_spots();
                    }
                    return fragmentEvent_spots;
                case 3:
                    if(fragmentEvent_humanity==null)
                    {
                        fragmentEvent_humanity=new FragmentEvent_humanity();
                    }
                    return fragmentEvent_humanity;
                case 4:
                    if(fragmentEvent_serve==null)
                    {
                        fragmentEvent_serve=new FragmentEvent_serve();
                    }
                    return fragmentEvent_serve;
                default:
                    return null;
            }


        }
    }
}

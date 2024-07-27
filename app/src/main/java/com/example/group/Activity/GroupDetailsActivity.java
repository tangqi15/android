package com.example.group.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Bean.AllGroupBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Bean.UserBean;
import com.example.group.Fragment.Fragment_Group.FragmentGroup_activity;
import com.example.group.Fragment.Fragment_Group.FragmentGroup_cite;
import com.example.group.Fragment.Fragment_Group.FragmentGroup_staff;
import com.example.group.Listener.TListener;
import com.example.group.Model.AddGroupCollectionModel;
import com.example.group.Model.CancelGroupCollectionModel;
import com.example.group.Model.GroupModel;
import com.example.group.Model.IsCollectionGroupModel;
import com.example.group.Model.JoinGroupModel;
import com.example.group.Model.MineModel;
import com.example.group.R;
import com.example.group.Server.Server;
import com.example.group.util.PagerSlidingTabStrip;
import com.squareup.picasso.Picasso;

//社团详情
public class GroupDetailsActivity extends BaseActivity {
TextView tv_group_details_name;
TextView tv_group_details_intro;
ImageView img_group_details_pic;
//收藏
    LinearLayout ly_collectiion;
    //收藏图片
    ImageView img_collect;
    ImageView img_back_group_detail;
    //收藏  标志位
    String isCollection;
Button btn_join_group;
    //活动
    private FragmentGroup_activity fragmentGroup_activity = null;
    //成员
    private FragmentGroup_staff fragmentGroup_staff = null;
    //表彰
    private FragmentGroup_cite fragmentGroup_cite = null;
    //当前屏幕的密度
    private DisplayMetrics dm;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
    //取 上一个页面的   group_id;
    public String group_id;

   //用户id
    public String user_id;

    //是否已经提交过订单
    String isJoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        initView();
        initEvent();
        isCollection();
        initGroupDetails();
        //如果头像为默认
        initIsCheckJoin();
        initPagerSlidingTabStrip();
        //判断用户是否已经加入了社团，当加入社团之后，加入社团则不显示
        initisjoin();
    }

    private void initGroupDetails() {

        GroupModel groupModel = new GroupModel();
        groupModel.getGroupInfo(group_id,GroupInfoCall);
    }

    private void isCollection() {
        group_id =getIntent().getStringExtra("group_id");
        IsCollectionGroupModel isCollectionGroupModel = new IsCollectionGroupModel();
         user_id = getUser_id();
        isCollectionGroupModel.getIsCollection(group_id,user_id,isCollectionCall);
    }
    private void initisjoin() {
        user_id =getUser_id();
        //是否加入了社团
        MineModel mineModel = new MineModel();
        mineModel.getUserInfo(user_id,tListener);
    }

    private void initView() {
        tv_group_details_name = findViewById(R.id.tv_group_details_name);
        img_group_details_pic = findViewById(R.id.img_group_details_pic);
        tv_group_details_intro = findViewById(R.id.tv_group_details_intro);
        btn_join_group = findViewById(R.id.btn_join_group);
        ly_collectiion = findViewById(R.id.ly_collectiion);
        img_collect = findViewById(R.id.img_collect);
        img_back_group_detail = findViewById(R.id.img_back_group_detail);


    }
    private void initEvent() {
        //加入社团按钮
        btn_join_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initIsUserHead();

            }
        });
        ly_collectiion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollection.equals("1")){
                    CancelGroupCollectionModel cancelGroupCollectionModel = new CancelGroupCollectionModel();
                    cancelGroupCollectionModel.getCanleCollection(group_id,user_id,cancelCollectionCall);
                }else if (isCollection.equals("0")){
                    AddGroupCollectionModel addGroupCollectionModel = new AddGroupCollectionModel();
                    addGroupCollectionModel.getAddCollection(group_id,user_id,addCollectionCall);
                }else{
                    Toast.makeText(GroupDetailsActivity.this,"错误操作", Toast.LENGTH_SHORT).show();
                }

            }
        });
        img_back_group_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPagerSlidingTabStrip() {
        //获取屏幕密度
        dm = getResources().getDisplayMetrics();
        viewPager = findViewById(R.id.viewPager);
      /*
      需要管理相互独立的并且隶属于 Activity 的Fragment使用 FragmentManager(),
      而在Fragment中动态添加Fragment要使用getChildFragmentManager()来管理。
       */
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        pagerSlidingTabStrip = (PagerSlidingTabStrip)findViewById(R.id.pagerslidingtabstrip);
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
        private String[] titles={"活动","成员"};//显示在二级导航上的标题文字
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
                    if(fragmentGroup_activity == null)
                    {
                        fragmentGroup_activity = new FragmentGroup_activity(GroupDetailsActivity.this);
                    }
                    return fragmentGroup_activity;
                case 1:
                    if(fragmentGroup_staff == null)
                    {
                        fragmentGroup_staff = new FragmentGroup_staff(GroupDetailsActivity.this);
                    }
                    return fragmentGroup_staff;
                default:
                    return null;
            }


        }
    }
    private void initIsCheckJoin() {
        JoinGroupModel joinGroupModel = new JoinGroupModel();
        joinGroupModel.getIsJoin(group_id,user_id,ttListener);
    }
    TListener<SuccessBean> ttListener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            isJoin = successBean.getSuccess();
            if ("1".equals(isJoin)){
                //Toast.makeText(GroupDetailsActivity.this,"已经提交过了，请耐心等待管理员同意。不要反复提交",Toast.LENGTH_SHORT).show();
                btn_join_group.setText("已提交申请");
            }

        }

        @Override
        public void onFail(String msg) {

        }
    };
    public String getUser_id() {
        SharedPreferences sp = GroupDetailsActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
    TListener<SuccessBean> isCollectionCall = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if ("1".equals(successBean.getSuccess())){
                isCollection = "1";
                img_collect.setImageResource(R.drawable.ic_collection_red);
            }else{
                isCollection = "0";
                img_collect.setImageResource(R.drawable.ic_collect);
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(GroupDetailsActivity.this,"收藏初始化失败", Toast.LENGTH_SHORT).show();
        }
    };

    //用户头像是否为空，为空去更换头像
    private void initIsUserHead() {
        MineModel mineModel = new MineModel();
        mineModel.getUserInfo(user_id,headListener);
    }
    TListener<SuccessBean> cancelCollectionCall = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if (successBean.getSuccess().equals("1")) {
                //刷新页面 重新调用一次方法
                isCollection();
                Toast.makeText(GroupDetailsActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(GroupDetailsActivity.this, "取消收藏失败1", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
                Toast.makeText(GroupDetailsActivity.this,"收藏初始化失败", Toast.LENGTH_SHORT).show();
                }
                };
     TListener<SuccessBean> addCollectionCall = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if (successBean.getSuccess().equals("1")) {
                //刷新页面 重新调用一次方法
                isCollection();
                Toast.makeText(GroupDetailsActivity.this, "添加收藏成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(GroupDetailsActivity.this, "添加收藏失败1", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
                Toast.makeText(GroupDetailsActivity.this,"收藏初始化失败", Toast.LENGTH_SHORT).show();
                }
                };

        //社团信息返回
        TListener<AllGroupBean> GroupInfoCall = new TListener<AllGroupBean>() {
            @Override
            public void onResponse(AllGroupBean allGroupBean) {
              String group_pic =allGroupBean.getGroup_pic();
              String group_name =allGroupBean.getGroup_name();
              String group_intro =allGroupBean.getGroup_intro();
              String group_type =allGroupBean.getGroup_type_name();

                tv_group_details_name.setText(group_name);
                Picasso.with(GroupDetailsActivity.this).load(Server.BASE_URL+"neu_soft_group/public/static/group_pic/"
                        +group_pic).into(img_group_details_pic);
                tv_group_details_intro.setText(group_intro);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(GroupDetailsActivity.this, "获取详情失败", Toast.LENGTH_SHORT).show();
            }
        };
    TListener<UserBean> headListener = new TListener<UserBean>() {
        @Override
        public void onResponse(UserBean userBean) {
            final String user_pic = userBean.getUser_pic();
            if ("moren.jpg".equals(user_pic)){
                //设置对话框标题
                new AlertDialog.Builder(GroupDetailsActivity.this).setTitle("系统提示")
                        //设置显示的内容
                        .setMessage("还没有设置头像，是否要去添加头像！")
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                Intent intent = new Intent(GroupDetailsActivity.this,HeadUpdateActivity.class);
                               intent.putExtra("user_id",user_id);
                               intent.putExtra("head_portrait","");
                                startActivity(intent);
                            }
                        }).setNegativeButton("不确定", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        Log.i("alertdialog"," 请保存数据！");
                    }
                }).show();//在按键响应事件中显示此对话框
            }else{
                Intent intent = new Intent(GroupDetailsActivity.this,JoinGroupActivity.class);
                intent.putExtra("group_id",group_id);
                startActivity(intent);
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(GroupDetailsActivity.this, "访问失败，请稍后再试", Toast.LENGTH_SHORT).show();
        }
    };

    TListener<UserBean> tListener = new TListener<UserBean>() {
        @Override
        public void onResponse(UserBean userBean) {
            //社团
           String group_id = userBean.getGroup_id();
            if (!"0".equals(group_id)){
                btn_join_group.setVisibility(View.GONE);
              //  Toast.makeText(GroupDetailsActivity.this,"社团编号为空",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(GroupDetailsActivity.this,"并没有数据返回",Toast.LENGTH_SHORT).show();
        }
    };
}
package com.example.group.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Adapter.ApplicantsPersonListAdapter;
import com.example.group.Bean.AllGroupBean;
import com.example.group.Bean.ApplicantsPersonListBean;
import com.example.group.Bean.TimeBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Model.ApplicantsPersonListModel;
import com.example.group.Model.GroupModel;
import com.example.group.Model.SignApplyModel;
import com.example.group.Model.TimeModel;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//活动详情
public class EventDetailsActivity extends BaseActivity {
    ImageView img_event_details_back;
    ImageView img_event_details_poster;
    TextView tv_activity_details_address;
    TextView tv_activity_description;
    TextView tv_activity_details_time;
    TextView tv_activity_source;
    TextView tv_activity_details_activity_name;
    TextView tv_number_applicants;
    RecyclerView recycle_applicants_person;
    Button btn_sign_apply;
    //社团编号
    String group_id;
    //活动编号
    String activity_id;
    //用户ID
    String user_id;
    //是否   已经参与
    String isSign;
    //跳转到社团详情
    LinearLayout ly_group_details_go;
    Button btn_go;
    ApplicantsPersonListAdapter applicantsPersonListAdapter;
    List<ApplicantsPersonListBean> items;
    //获取报名人数   这个Model 会使用好多次   所以 提出来
    ApplicantsPersonListModel applicantsPersonListModel = new ApplicantsPersonListModel();
    //  报名  Model
    SignApplyModel signApplyModel = new SignApplyModel();
    //活动结束时间
    String activity_finish_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        initView();
        initEvent();
        initBand();
        initIsCheckSign();
    }

    private void initIsCheckSign() {
        signApplyModel.getIsSignMessage(user_id,activity_id,isSignListener);
    }

    private void initView() {
        img_event_details_back = findViewById(R.id.img_event_details_back);
        img_event_details_poster = findViewById(R.id.img_event_details_poster);
        tv_activity_details_address = findViewById(R.id.tv_activity_details_address);
        tv_activity_details_time = findViewById(R.id.tv_activity_details_time);
        tv_activity_description = findViewById(R.id.tv_activity_description);
        tv_activity_details_activity_name = findViewById(R.id.tv_activity_details_activity_name);
        tv_number_applicants = findViewById(R.id.tv_number_applicants);
        recycle_applicants_person = findViewById(R.id.recycle_applicants_person);
        tv_activity_source = findViewById(R.id.tv_activity_source);
        btn_sign_apply = findViewById(R.id.btn_sign_apply);
        //跳转到  社团详情页面
        ly_group_details_go = findViewById(R.id.ly_group_details_go);
        btn_go = findViewById(R.id.btn_go);
        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(EventDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_applicants_person.setLayoutManager(linearLayoutManager);
        applicantsPersonListAdapter = new ApplicantsPersonListAdapter(EventDetailsActivity.this,items,R.layout.event_details_item);
        recycle_applicants_person.setAdapter(applicantsPersonListAdapter);
        recycle_applicants_person.setItemAnimator(new DefaultItemAnimator());
        //获取  上个页面传过来的值
        activity_id = getIntent().getStringExtra("activity_id");
        String activity_name = getIntent().getStringExtra("activity_name");
        String activity_address = getIntent().getStringExtra("activity_address");
        String activity_pic = getIntent().getStringExtra("activity_pic");
        String activity_start_time = getIntent().getStringExtra("activity_start_time");
        //活动结束时间
         activity_finish_time = getIntent().getStringExtra("activity_finish_time");
        String activity_intro = getIntent().getStringExtra("activity_intro");
        //是否允许报名
        String activity_is_apply = getIntent().getStringExtra("activity_is_apply");

        //    社团ID
         group_id = getIntent().getStringExtra("group_id");
        //给控件  绑定指
        //活动地址
        tv_activity_details_address.setText(activity_address);
        //活动开始时间到活动结束时间
        tv_activity_details_time.setText(activity_start_time+"--"+activity_finish_time);
        //活动的名称
        tv_activity_details_activity_name.setText(activity_name);
        //活动简介
        tv_activity_description.setText(activity_intro);
        Picasso.with(EventDetailsActivity.this).load(Server.BASE_URL+
                "neu_soft_group/public/static/activity_pic/"+activity_pic).
                into(img_event_details_poster);
        user_id = getUser_id();
        //如果不允许报名，报名按钮不可点击
        if(activity_is_apply.equals("0")){
            btn_sign_apply.setEnabled(false);
            btn_sign_apply.setText("不可以报名");
        }
      //  if (activity_finish_time>)

    //    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
      //  Date date = new Date(System.currentTimeMillis());
      //  btn_sign_apply.setText("Date获取当前日期时间"+simpleDateFormat.format(date));


        //获取服务器时间
        TimeModel timeModel = new TimeModel();
        timeModel.getTime(timeListener);
    }
    private void initEvent() {
        img_event_details_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventDetailsActivity.this,GroupDetailsActivity.class);
                intent.putExtra("group_id",group_id);

                startActivity(intent);
            }
        });
        //当点击  我要报名的时候，弹出一个 框子，  防止  点错 操作
        btn_sign_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(EventDetailsActivity.this,SignApplyActivity.class);
                startActivity(intent);*/
                //设置对话框标题
                new AlertDialog.Builder(EventDetailsActivity.this).setTitle("系统提示")
                        //设置显示的内容
                        .setMessage("是否要 参与活动！")
                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                initIsCheckSign();
                                if ("1".equals(isSign)){
                                    Toast.makeText(EventDetailsActivity.this,"已经参加，请不要重复操作！！！",Toast.LENGTH_SHORT).show();
                                }else{

                                    signApplyModel.getSignMessage(user_id,activity_id,ttListener);
                                }
                            }
                        }).setNegativeButton("不确定", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        Log.i("alertdialog"," 请保存数据！");
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });
    }
    private void initBand() {
        //获取报名列表
        applicantsPersonListModel.getApplicantsPersonList(activity_id,listListener);
        //获取活动发起者的  信息
        GroupModel groupModel = new GroupModel();
        groupModel.getGroupInfo(group_id,tListener);

    }
    //获取  报名列表的   返回值
    ListListener<ApplicantsPersonListBean> listListener = new ListListener<ApplicantsPersonListBean>() {
        @Override
        public void onResponse(List<ApplicantsPersonListBean> list) {
            //获取  参加报名人数
           // tv_number_applicants.setText(list.size());
            applicantsPersonListAdapter.setList(list);
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(EventDetailsActivity.this,"获取ApplicantsPersonList失败！！！",Toast.LENGTH_SHORT).show();
        }
    };
    //  获得   活动发起者  的返回值 处理
    TListener<AllGroupBean> tListener = new TListener<AllGroupBean>() {
        @Override
        public void onResponse(AllGroupBean allGroupBean) {
           String activity_source =  allGroupBean.getGroup_name();

            //活动发起者
            tv_activity_source.setText(activity_source);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(EventDetailsActivity.this,"获取AllGroupBean失败！！！",Toast.LENGTH_SHORT).show();
        }
    };

    //活动结束时间与服务器时间进行比对    timeListener
    TListener<TimeBean> timeListener = new TListener<TimeBean>() {
        @Override
        public void onResponse(TimeBean timeBean) {

            String time =  timeBean.getTime();
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date=sDateFormat.parse(activity_finish_time);
                //当前时间
                Date times=sDateFormat.parse(time);
                if (times.getTime()>date.getTime()){
                    btn_sign_apply.setVisibility(View.GONE);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                Toast.makeText(EventDetailsActivity.this,"出现异常，请稍后再试",Toast.LENGTH_SHORT).show();
                btn_sign_apply.setVisibility(View.GONE);
            }
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(EventDetailsActivity.this,"请检查网络，请稍后再试,报名不完整",Toast.LENGTH_SHORT).show();
        }
    };
    //报名之后的  返回值 处理
    TListener<SuccessBean> ttListener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if ("1".equals(successBean.getSuccess())){
                Toast.makeText(EventDetailsActivity.this,"报名成功",Toast.LENGTH_SHORT).show();
                //刷新报名列表
                //获取报名人数
                applicantsPersonListModel.getApplicantsPersonList(activity_id,listListener);
            }else{
                Toast.makeText(EventDetailsActivity.this,"报名失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(EventDetailsActivity.this,"报名失败，请检查网络是否连接",Toast.LENGTH_SHORT).show();

        }
    };
    //   是否已经  参与活动的     返回值
    TListener<SuccessBean> isSignListener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            isSign = successBean.getSuccess();
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(EventDetailsActivity.this,"获取是否已经参加失败！",Toast.LENGTH_SHORT).show();
        }
    };
    public String getUser_id() {
        SharedPreferences sp = EventDetailsActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
}

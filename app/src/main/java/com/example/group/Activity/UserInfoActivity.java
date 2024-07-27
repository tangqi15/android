package com.example.group.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Bean.UserBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.MineModel;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

public class UserInfoActivity extends BaseActivity {
    //个人信息
TextView user_info_screen;
TextView user_info_sex;
TextView tv_user_info_username;
TextView user_info_phone;
ImageView img_user_head_pic;
ImageView img_user_head_pic_back;
LinearLayout Line_head_update;
LinearLayout Line_screen_update;
LinearLayout Line_sex_update;
    //修改密码
    LinearLayout linearLayout_mine_update_password;
    //头像
    String head_portrait;
    //账号
    String username;
    //网名
    String nickname;
    //性别
    String sex;
    //用户id
    String user_id;
    //手机号
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        initEvent();
        initSend();
    }

    private void initSend() {
        String user_id = getUser_id();
        MineModel mineModel = new MineModel();
        mineModel.getUserInfo(user_id,tListener);
    }

    private void initView() {
        user_info_screen = findViewById(R.id.user_info_screen);
        user_info_sex = findViewById(R.id.user_info_sex);
        tv_user_info_username = findViewById(R.id.tv_user_info_username);
        user_info_phone = findViewById(R.id.user_info_phone);
        img_user_head_pic = findViewById(R.id.img_user_head_pic);
        img_user_head_pic_back = findViewById(R.id.img_user_head_pic_back);
        Line_head_update = findViewById(R.id.Line_head_update);
        Line_screen_update = findViewById(R.id.Line_screen_update);
        Line_sex_update = findViewById(R.id.Line_sex_update);
        linearLayout_mine_update_password = findViewById(R.id.linearLayout_mine_update_password);

    }

    private void initEvent() {
        img_user_head_pic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //修改头像
        Line_head_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,HeadUpdateActivity.class);
                intent.putExtra("user_id",user_id);
                intent.putExtra("head_portrait",head_portrait);
                startActivity(intent);
            }
        });
        //修改昵称
        Line_screen_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,ScreenUpdateActivity.class);
                intent.putExtra("nickname",nickname);
                startActivity(intent);
            }
        });
        //修改性别
        Line_sex_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,SexUpdateActivity.class);
                intent.putExtra("sex",sex);
                startActivity(intent);
            }
        });
        //修改密码
        linearLayout_mine_update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, PasswordUpdateActivity.class);
                startActivity(intent);
            }
        });

    }

    TListener<UserBean> tListener = new TListener<UserBean>() {
        @Override
        public void onResponse(UserBean userBean) {
            //头像
            head_portrait = userBean.getUser_pic();
            //账号
            username = userBean.getUsername();
            //性别
            sex = userBean.getSex_name();
            //昵称
            nickname = userBean.getScreen();
            user_id = userBean.getUser_id();
            phone = userBean.getPhone();
            if ("".equals(head_portrait)){

            }else{
                Picasso.with(UserInfoActivity.this).load(Server.BASE_URL+"neu_soft_group/public/static/user_head/"+head_portrait)
                        .resize(200,200).into(img_user_head_pic);
            }
            nickname = userBean.getScreen();
            user_info_screen.setText(nickname);
            user_info_sex.setText(sex);
            tv_user_info_username.setText(username);
            user_info_phone.setText(phone);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(UserInfoActivity.this,"并没有数据返回",Toast.LENGTH_SHORT).show();
        }
    };
    public String getUser_id() {
        SharedPreferences sp = UserInfoActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
}

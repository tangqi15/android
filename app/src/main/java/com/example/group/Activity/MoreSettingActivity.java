package com.example.group.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.group.R;


//更多设置
public class MoreSettingActivity extends BaseActivity {
ImageView img_back_mine;
//屏蔽管理
/*LinearLayout linearLayout_mine_block;*/
//意见反馈
//LinearLayout linearLayout_mine_opinion;
//建议反馈
LinearLayout linearLayout_mine_with;

//退出登录
    Button btn_layout;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_setting);
        initView();
        initEvent();

    }




    private void initView() {
        img_back_mine = findViewById(R.id.img_back_mine);
       /* 屏蔽管理
        linearLayout_mine_block = findViewById(R.id.linearLayout_mine_block);*/
       // linearLayout_mine_opinion = findViewById(R.id.linearLayout_mine_opinion);
        linearLayout_mine_with = findViewById(R.id.linearLayout_mine_with);

        btn_layout = findViewById(R.id.btn_layout);
    }

    private void initEvent() {
        img_back_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //退出登录
        btn_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtyContainer atyContainer = new AtyContainer();
                atyContainer.finishAllActivity();
                Intent intent = new Intent(MoreSettingActivity.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(MoreSettingActivity.this,"注销成功",Toast.LENGTH_SHORT).show();
            }
        });
        //屏蔽管理
       /* linearLayout_mine_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        //意见反馈
    /*    linearLayout_mine_opinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreSettingActivity.this,FeedBackActivity.class);
                startActivity(intent);
            }
        });*/
        //投诉建议
        linearLayout_mine_with.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreSettingActivity.this,FeedBackActivity.class);
                startActivity(intent);
            }
        });

    }


}

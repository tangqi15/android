package com.example.group.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

public class UserHeadPicActivity extends BaseActivity {
//显示大头像

    ImageView img_user_head_pic;
    ImageView img_user_head_pic_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_head_pic);
        initView();
        initEvent();
    }



    private void initView() {
        img_user_head_pic = findViewById(R.id.img_user_head_pic);
        img_user_head_pic_back = findViewById(R.id.img_user_head_pic_back);

       String head_portrait =  getIntent().getStringExtra("head_portrait");

        Picasso.with(UserHeadPicActivity.this).load(Server.BASE_URL+"neu_soft_group/public/static/user_head/"+head_portrait)
                .resize(1000,1000).into(img_user_head_pic);
    }

    private void initEvent() {
        img_user_head_pic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

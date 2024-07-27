package com.example.group.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.FeedBackModel;
import com.example.group.R;
//问题反馈
public class FeedBackActivity extends BaseActivity {
    ImageView img_feed_back_back;
    TextView tv_about_feed_back;
    EditText edt_feed_content;
    Button btn_feed_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initView();
        initEvent();
    }


    private void initView() {
        img_feed_back_back = findViewById(R.id.img_feed_back_back);
        tv_about_feed_back = findViewById(R.id.tv_about_feed_back);
        edt_feed_content = findViewById(R.id.edt_feed_content);
        btn_feed_content = findViewById(R.id.btn_feed_content);
        tv_about_feed_back.setText("           有问题，可以发送邮件“616992443@qq.com”\n" +
                "           也可以添加微信，下面有微信的二维码。");
    }


    private void initEvent() {
        img_feed_back_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_feed_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feed_content = edt_feed_content.getText().toString();
                if ("".equals(feed_content)){
                    Toast.makeText(FeedBackActivity.this,"请输入举报内容",Toast.LENGTH_SHORT).show();
                }else{
                    String user_id = getUser_id();
                    FeedBackModel feedBackModel = new FeedBackModel();
                    feedBackModel.getSubmitFeed(user_id,feed_content,listener);
                }
            }
        });

    }
    TListener<SuccessBean> listener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if ("1".equals(successBean.getSuccess())){
                Toast.makeText(FeedBackActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FeedBackActivity.this,Main2Activity.class);
                startActivity(intent);
            }else{
                Toast.makeText(FeedBackActivity.this,"提交失败",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(FeedBackActivity.this,"提交失败，请检查网络是否连接",Toast.LENGTH_SHORT).show();
        }
    };
    public String getUser_id() {
        SharedPreferences sp = FeedBackActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
}

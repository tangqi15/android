package com.example.group.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Bean.SuccessBean;
import com.example.group.Bean.UserBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.JoinGroupModel;
import com.example.group.Model.MineModel;
import com.example.group.R;
//加入社团
public class JoinGroupActivity extends BaseActivity {
ImageView img_join_group_back;
EditText tv_actual_join_user_name;
EditText tv_actual_join_user_phone;
EditText tv_actual_join_group_reason;
Button btn_join_apply;
String group_id;
String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        initView();
        initEvent();


    }





    private void initView() {
        img_join_group_back = findViewById(R.id.img_join_group_back);
        tv_actual_join_user_name = findViewById(R.id.tv_actual_join_user_name);
        tv_actual_join_user_phone = findViewById(R.id.tv_actual_join_user_phone);
        tv_actual_join_group_reason = findViewById(R.id.tv_actual_join_group_reason);
        btn_join_apply = findViewById(R.id.btn_join_apply);
        user_id = getUser_id();
        group_id = getIntent().getStringExtra("group_id");
    }


    private void initEvent() {
        img_join_group_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_join_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinGroupModel joinGroupModel = new JoinGroupModel();
                //真实姓名
                String user_name    = tv_actual_join_user_name.getText().toString();
                //联系方式
                String user_phone   = tv_actual_join_user_phone.getText().toString();
                //想要加入社团的原因
                String group_reason = tv_actual_join_group_reason.getText().toString();
                if ("".equals(user_name)){
                    Toast.makeText(JoinGroupActivity.this,"请输入真实姓名",Toast.LENGTH_SHORT).show();
                }else if("".equals(user_phone)){
                    Toast.makeText(JoinGroupActivity.this,"请输入联系方式，方便联系",Toast.LENGTH_SHORT).show();
                }else{
                    joinGroupModel.getJoinGroupMessage(user_name,user_phone,group_reason,group_id,user_id,tListener);
                }

            }
        });
    }

    TListener<SuccessBean> tListener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if ("1".equals(successBean.getSuccess())){
                Toast.makeText(JoinGroupActivity.this,"申请提交成功，请耐心等待",Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(JoinGroupActivity.this,"申请提交失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(JoinGroupActivity.this,"发送失败，请检查网络是否连接成功",Toast.LENGTH_SHORT).show();
        }
    };

    public String getUser_id() {
        SharedPreferences sp = JoinGroupActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }


}

package com.example.group.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.PhoneVerifyModel;
import com.example.group.R;

public class FindPassActivity extends BaseActivity {
   EditText find_input_phone_et;
   EditText find_input_username_et;
   Button find_commit_btn;
   Button btn_find_pass_back;
   //手机号
    String phone;
    //账号
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass);
        initView();
        initEvent();
    }



    private void initView() {
        find_input_username_et = findViewById(R.id.find_input_username_et);
        find_input_phone_et = findViewById(R.id.find_input_phone_et);
        find_commit_btn = findViewById(R.id.find_commit_btn);
        btn_find_pass_back = findViewById(R.id.btn_find_pass_back);
    }
    private void initEvent() {
        find_commit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 phone = find_input_phone_et.getText().toString().trim();
                 username = find_input_username_et.getText().toString().trim();
                if ("".equals(phone)){
                    Toast.makeText(FindPassActivity.this,"手机号不能为空",Toast.LENGTH_SHORT).show();
                }else if ("".equals(username)){
                    Toast.makeText(FindPassActivity.this,"账号不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    PhoneVerifyModel phoneVerifyModel = new PhoneVerifyModel();
                    phoneVerifyModel.getVerifyPhoneMessage(username,phone,VerigyMessage);
                }

            }
        });
        btn_find_pass_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    TListener<SuccessBean> VerigyMessage = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if (successBean.getSuccess().equals("1")){
                Intent intent = new Intent(FindPassActivity.this,FindPassActivity2.class);
                intent.putExtra("phone",phone);
                intent.putExtra("username",username);
                startActivity(intent);
            }else{
                Toast.makeText(FindPassActivity.this,"此账号并未绑定这个手机号",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(FindPassActivity.this,"服务器留神了，请稍后再试",Toast.LENGTH_SHORT).show();
        }
    };
}

package com.example.group.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.RegisterModel;
import com.example.group.R;
import com.mob.MobSDK;


import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity2 extends BaseActivity {
    //用户名
EditText edt_username;
//密码
EditText edt_pass1;
EditText edt_pass2;
//网名
EditText edt_screen;
//注册
Button btn_register;
//绑定成功的手机号
String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        initView();
        initEvent();
    }



    private void initView() {
        edt_username = findViewById(R.id.edt_username);
        edt_pass1    = findViewById(R.id.edt_pass1);
        edt_pass2    = findViewById(R.id.edt_pass2);
        edt_screen   = findViewById(R.id.edt_screen);
        btn_register = findViewById(R.id.btn_register);
         phone = getIntent().getStringExtra("phone");
    }

    private void initEvent() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //.trim()  去掉字符串两端的多余的空格
                String username = edt_username.getText().toString().trim();
                String pass1 = edt_pass1.getText().toString().trim();
                String pass2 = edt_pass2.getText().toString().trim();
                String screen = edt_screen.getText().toString().trim();
                if ("".equals(username)){
                    Toast.makeText(RegisterActivity2.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if("".equals(pass1)){
                    Toast.makeText(RegisterActivity2.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if ("".equals(pass2)){
                    Toast.makeText(RegisterActivity2.this,"确认密码不能为空",Toast.LENGTH_SHORT).show();
                }else if ("".equals(screen)){
                    Toast.makeText(RegisterActivity2.this,"网名不能为空",Toast.LENGTH_SHORT).show();
                }else if (screen.equals("管理员")){
                    Toast.makeText(RegisterActivity2.this,"网名不能设置为 管理员",Toast.LENGTH_SHORT).show();
                }else{
                    if (!pass1.equals(pass2)){
                        Toast.makeText(RegisterActivity2.this,"两次密码不一致，请确认",Toast.LENGTH_SHORT).show();
                    }else {
                        RegisterModel registerModel = new RegisterModel();
                        registerModel.getRegister(username,pass1,screen,phone,RegMessageList);
                    }
                }
            }
        });
    }

TListener<SuccessBean> RegMessageList = new TListener<SuccessBean>() {
    @Override
    public void onResponse(SuccessBean successBean) {
        if (successBean.getSuccess().equals("1")){
            Toast.makeText(RegisterActivity2.this,"注册成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity2.this,LoginActivity.class);
            startActivity(intent);
        }else if(successBean.getSuccess().equals("2")) {
            Toast.makeText(RegisterActivity2.this, "账号已经被使用，请更换账号", Toast.LENGTH_SHORT).show();
        } else{
                Toast.makeText(RegisterActivity2.this,"注册失败，请稍后再注册",Toast.LENGTH_SHORT).show();

            }
        }


    @Override
    public void onFail(String msg) {
        Toast.makeText(RegisterActivity2.this,"注册失败，请稍后再注册",Toast.LENGTH_SHORT).show();
    }
};
}


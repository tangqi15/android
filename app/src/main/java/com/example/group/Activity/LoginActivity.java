package com.example.group.Activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Bean.LoginBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.LoginModel;
import com.example.group.R;

public class LoginActivity extends BaseActivity {
TextView tv_reg;
EditText edt_username;
EditText edt_password;
CheckBox checkBox_login;
Button but_log;
//关于我们
TextView about_mine;
//忘记密码
TextView forget_password;
String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
        initChenk();

    }



    private void initView() {

        tv_reg  = findViewById(R.id.tv_reg);
        edt_username  = findViewById(R.id.edt_username);
        edt_password  = findViewById(R.id.edt_password);
        checkBox_login  = findViewById(R.id.checkBox_login);
        but_log  = findViewById(R.id.but_log);
        about_mine  = findViewById(R.id.about_mine);
        forget_password  = findViewById(R.id.forget_password);
    }

    private void initEvent() {
        //注册
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //关于我们
        about_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,WithAboutActivity.class);
                startActivity(intent);
            }
        });
        //忘记密码
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindPassActivity.class);
                startActivity(intent);
            }
        });
        //登陆
        but_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginModel loginModel = new LoginModel();
                loginModel.getUserLogMessage(edt_username.getText().toString(),edt_password.getText().toString(),tListener);
            }
        });

    }
    TListener<LoginBean> tListener = new TListener<LoginBean>() {
        @Override
        public void onResponse(LoginBean loginBean) {
            String a = loginBean.getSuccess();
            user_id = loginBean.getUser_id();
            if (a.equals("1")){
                saveUserinfo();

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            }else if(a.equals("2")){
                Toast.makeText(LoginActivity.this,"账户不存在",Toast.LENGTH_SHORT).show();
            }else if(a.equals("3")){
                Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
            }else if(a.equals("4")){
                Toast.makeText(LoginActivity.this
                        ,"已经被限制登录限制登录，请再"+loginBean.getSeal_time()+"之后登录"
                        ,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(LoginActivity.this,"网络请求失败，请查看服务器是否打开",Toast.LENGTH_SHORT).show();
        }
    };


    public void saveUserinfo() {
        SharedPreferences.Editor editor = getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit();
        editor.putString("user_id",user_id);
        editor.putBoolean("remember", checkBox_login.isChecked());
        if (checkBox_login.isChecked()) {
            editor.putString("username", edt_username.getText().toString());
            editor.putString("password", edt_password.getText().toString());
        }
        editor.commit();
    }

    private void initChenk() {
        boolean flag = getUer_remember();
        String username = getuser();
        String passwor = getpass();
        if (flag) {
            edt_username.setText(username);
            edt_password.setText(passwor);
            checkBox_login.setChecked(flag);
        }
    }
    public Boolean getUer_remember(){
        SharedPreferences sp;
        sp=getSharedPreferences("userInfo",MODE_PRIVATE);
        return sp.getBoolean("remember",false);
    }
    //获取用户名
    public String getuser(){
        SharedPreferences sp;  //  sharedPrefercences英文意思   轻量级存储
        sp=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        //表示userInfo 是 Context.MODE_PRIVATE  枚举类型   枚举就是一一列举
        //Context  是android中的对象
        //Context.MODE_PRIVATE是  android中的枚举，代表该文件  是私有文件，只能被应用本身访问
        return sp.getString("username","");
    }

    public String getpass(){
        SharedPreferences sp;
        sp=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        // 调用Context对象的getSharedPreferences()方法获得的SharedPreferences对象可以被同一应用程序下的其他组件共享.
        // 调用Activity对象的getPreferences()方法获得的SharedPreferences对象只能在该Activity中使用.
        return sp.getString("password","");
    }
}

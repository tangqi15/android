package com.example.group.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group.R;
import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class FindPassActivity2 extends BaseActivity implements View.OnClickListener{
    String APPKEY = "2a74cb61f2fd0";
    String APPSECRET = "778568e31e25e5d1e8bbfecf99d3d489";


    // 验证码输入框
    private EditText inputCodeEt;

    // 获取验证码按钮
    private Button requestCodeBtn;

    // 注册按钮
    private Button commitBtn;

    private Button btn_find_pass2_back;
    private Button btn_pass2_back_log;

    //倒计时显示   可以手动更改。
    int i = 30;

    String phone;

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass2);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {

        inputCodeEt = (EditText) findViewById(R.id.find_input_code_et2);
        requestCodeBtn = (Button) findViewById(R.id.find_request_code_btn2);
        commitBtn = (Button) findViewById(R.id.find_commit_btn2);
        btn_find_pass2_back = (Button) findViewById(R.id.btn_find_pass2_back);
        btn_pass2_back_log = (Button) findViewById(R.id.btn_pass2_back_log);
        phone = getIntent().getStringExtra("phone");
        username = getIntent().getStringExtra("username");
        //获取验证码点击
        requestCodeBtn.setOnClickListener(this);
        //注册点击
        commitBtn.setOnClickListener(this);

        btn_find_pass2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_pass2_back_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindPassActivity2.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        // 启动短信验证sdk
        MobSDK.init(this, APPKEY, APPSECRET);
        EventHandler eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.find_request_code_btn2:
                // 1. 判断手机号是不是11位并且看格式是否合理
                if (!judgePhoneNums(phone)) {
                    return;
                } // 2. 通过sdk发送短信验证

                SMSSDK.getVerificationCode("86", phone);

                // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                requestCodeBtn.setClickable(false);
                requestCodeBtn.setText("重新发送(" + i + ")");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        handler.sendEmptyMessage(-8);
                    }
                }).start();
                break;

            case R.id.find_commit_btn2:
                //将收到的验证码和手机号提交再次核对
                SMSSDK.submitVerificationCode("86", phone, inputCodeEt
                        .getText().toString());
                break;
        }
    }


    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                requestCodeBtn.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                requestCodeBtn.setText("获取验证码");
                requestCodeBtn.setClickable(true);
                i = 30;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(FindPassActivity2.this,
                                FindPassActivity3.class);
                        intent.putExtra("username",username);
                        startActivity(intent);

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "正在获取验证码",
                                Toast.LENGTH_SHORT).show();
                    } else {
                       // Toast.makeText(FindPassActivity2.this,"验证码不正确",Toast.LENGTH_SHORT).show();
                      //  ((Throwable) data).printStackTrace();
                    }
                }
                else{
                    Toast.makeText(FindPassActivity2.this,"验证码不正确123",Toast.LENGTH_SHORT).show();
                }

            }
        }
    };


    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！",Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 判断一个字符串的位数
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][3578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    @Override
    protected void onDestroy() {
        //反注册回调监听接口
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }

}




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
import android.widget.Toast;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.UpdatePasswordModel;
import com.example.group.R;

public class PasswordUpdateActivity extends BaseActivity {
ImageView img_update_password_back;
EditText edt_old_pass;
EditText edt_new_pass1;
EditText edt_new_pass2;
Button btn_update_pass_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_upate);
        initView();
        initEvent();
    }



    private void initView() {
        img_update_password_back = findViewById(R.id.img_update_password_back);
        edt_old_pass = findViewById(R.id.edt_old_pass);
        edt_new_pass1 = findViewById(R.id.edt_new_pass1);
        edt_new_pass2 = findViewById(R.id.edt_new_pass2);
        btn_update_pass_submit = findViewById(R.id.btn_update_pass_submit);
    }

    private void initEvent() {
        img_update_password_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_update_pass_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old_pass = edt_old_pass.getText().toString();
                String new_pass1 = edt_new_pass1.getText().toString();
                String new_pass2 = edt_new_pass2.getText().toString();
                if ("".equals(old_pass)||"".equals(new_pass1)||"".equals(new_pass2)){
                    Toast.makeText(PasswordUpdateActivity.this,"新旧密码都不能为空",Toast.LENGTH_SHORT).show();
                }else if (!new_pass1.equals(new_pass2)){
                    Toast.makeText(PasswordUpdateActivity.this,"新密码不一致",Toast.LENGTH_SHORT).show();
                }else {
                    UpdatePasswordModel updatePasswordModel = new UpdatePasswordModel();
                    String user_id = getUser_id();
                    updatePasswordModel.getUpdatePasswordMessage(old_pass,new_pass1,user_id,updatePasswordCallListener);
                }
            }
        });
    }
    TListener<SuccessBean> updatePasswordCallListener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if ("2".equals(successBean.getSuccess())){
                Toast.makeText(PasswordUpdateActivity.this,"旧密码不一致",Toast.LENGTH_SHORT).show();
            }else if ("1".equals(successBean.getSuccess())){
                Toast.makeText(PasswordUpdateActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PasswordUpdateActivity.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(PasswordUpdateActivity.this,"密码修改成功，请重新登录",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(PasswordUpdateActivity.this,"修改失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(PasswordUpdateActivity.this,"修改失败，请检查网络是否连接",Toast.LENGTH_SHORT).show();
        }
    };
    public String getUser_id() {
        SharedPreferences sp = PasswordUpdateActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
}

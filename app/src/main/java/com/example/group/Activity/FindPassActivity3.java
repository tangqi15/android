package com.example.group.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.UpdatePasswordModel;
import com.example.group.R;

public class FindPassActivity3 extends BaseActivity {
EditText new_pass1;
EditText new_pass2;
Button btn_update_pass_submit2;
Button btn_find_pass3_back;
Button btn_pass3_back_log;

String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass3);
        initView();
        initEvent();
    }


    private void initView() {
        new_pass1 = findViewById(R.id.new_pass1);
        new_pass2 = findViewById(R.id.new_pass2);
        btn_pass3_back_log = findViewById(R.id.btn_pass3_back_log);
        btn_find_pass3_back = findViewById(R.id.btn_find_pass3_back);
        btn_update_pass_submit2 = findViewById(R.id.btn_update_pass_submit2);
        username = getIntent().getStringExtra("username");
      //  Toast.makeText(FindPassActivity3.this,username+"用户名",Toast.LENGTH_SHORT).show();
    }

    private void initEvent() {
        btn_update_pass_submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass1 = new_pass1.getText().toString().trim();
                String pass2 = new_pass2.getText().toString().trim();
                if ("".equals(pass1)||"".equals(pass2)){
                    Toast.makeText(FindPassActivity3.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(!pass1.equals(pass2)){
                    Toast.makeText(FindPassActivity3.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                }else{
                    UpdatePasswordModel updatePasswordModel = new UpdatePasswordModel();
                    updatePasswordModel.getNewPasswordMessage(pass1,username,UpdateNewPassListener);
                }
            }
        });
        btn_find_pass3_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_pass3_back_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindPassActivity3.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
TListener<SuccessBean> UpdateNewPassListener = new TListener<SuccessBean>() {
    @Override
    public void onResponse(SuccessBean successBean) {
        if (successBean.getSuccess().equals("1")){
            Toast.makeText(FindPassActivity3.this,"新密码设置成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FindPassActivity3.this,LoginActivity.class);
            startActivity(intent);
        }else if (successBean.getSuccess().equals("3")){
            Toast.makeText(FindPassActivity3.this,"设置的新密码和旧密码一致",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(FindPassActivity3.this,"新密码设置失败，请稍后再试",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(FindPassActivity3.this,"新密码设置失败，请检查网络",Toast.LENGTH_SHORT).show();
    }
};
}

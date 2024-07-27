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
import com.example.group.Model.UpdateScreenModel;
import com.example.group.R;

//修改昵称
public class ScreenUpdateActivity extends BaseActivity {
ImageView img_update_screen_back;
EditText edt_update_screen;
Button btn_submit_update_screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_update);
        initView();
        initEvent();
    }

    private void initView() {
        img_update_screen_back = findViewById(R.id.img_upate_screen_back);
        edt_update_screen = findViewById(R.id.edt_update_screen);
        btn_submit_update_screen = findViewById(R.id.btn_submit_upate_screen);

    }
    private void initEvent() {
        img_update_screen_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击修改昵称   的按钮
        btn_submit_update_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String update_screen = edt_update_screen.getText().toString();
                if ("".equals(update_screen)){
                    Toast.makeText(ScreenUpdateActivity.this,"请输入想要修改的昵称，不能为空哦",Toast.LENGTH_SHORT).show();
                }else{
                    UpdateScreenModel updateScreenModel = new UpdateScreenModel();
                    String user_id = getUser_id();
                    updateScreenModel.getUpdateScreenMessage(update_screen,user_id,updateScreenListener);
                }
            }
        });

    }
    TListener<SuccessBean> updateScreenListener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if ("1".equals(successBean.getSuccess())){
                Toast.makeText(ScreenUpdateActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ScreenUpdateActivity.this,Main2Activity.class);
                startActivity(intent);
            }else{
                Toast.makeText(ScreenUpdateActivity.this,"修改失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
        Toast.makeText(ScreenUpdateActivity.this,"修改失败，请检查网络是否连接",Toast.LENGTH_SHORT).show();
        }
    };
    public String getUser_id() {
        SharedPreferences sp = ScreenUpdateActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }

}

package com.example.group.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Bean.SexBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Model.UserSexModel;
import com.example.group.R;

import java.util.ArrayList;
import java.util.List;

public class SexUpdateActivity extends BaseActivity {

    Spinner spi;
    ArrayAdapter<String> arrayAdapter;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    String[] array;
    //性别Model
    UserSexModel userSexModel = new UserSexModel();
    //用户ID
    String user_id;
    //设置变量  用于存储
    String updateLaterSex;
    Button btn_sex_update_submit;
    TextView tv_present_sex;
    ImageView img_sex_update_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex_update);
        initView();
        initEvent();
        initAllSex();
    }

    private void initAllSex() {
        userSexModel.getAllSex(AllSexListListener);
    }



    private void initView() {
        spi = findViewById(R.id.spi);
        img_sex_update_back = findViewById(R.id.img_sex_update_back);
        btn_sex_update_submit = findViewById(R.id.btn_sex_update_submit);
        tv_present_sex = findViewById(R.id.tv_present_sex);
        //从轻量级存储中  取出   用户id
        user_id= getUser_id();
    }
    private void initEvent() {
        img_sex_update_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_sex_update_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("男".equals(updateLaterSex)){
                    userSexModel.getUpdateSex(user_id,"1",SubmitCallListener);
                }else if("女".equals(updateLaterSex)){
                    userSexModel.getUpdateSex(user_id,"2",SubmitCallListener);
                }else{
                    userSexModel.getUpdateSex(user_id,"3",SubmitCallListener);
                }

            }
        });
        String presentSex = getIntent().getStringExtra("sex");
        tv_present_sex.setText(presentSex);
    }
    TListener<SuccessBean> SubmitCallListener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            if ("1".equals(successBean.getSuccess())){
                Toast.makeText(SexUpdateActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(SexUpdateActivity.this,Main2Activity.class);
                startActivity(intent);
            }else{
                Toast.makeText(SexUpdateActivity.this,"修改失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(SexUpdateActivity.this,"修改失败，请检查网络",Toast.LENGTH_SHORT).show();
        }
    };



ListListener<SexBean> AllSexListListener = new ListListener<SexBean>() {
    @Override
    public void onResponse(List<SexBean> list) {
        int i=list.size();
        String[] str = new String[i];
        ArrayList<String> list1=new ArrayList<String>();
       // list1.add("");
        for(int j=0;j<i;j++){
            String sexName =  list.get(j).getSex_name();
            list1.add(sexName);
        }
        array = list1.toArray(new String[list1.size()]);


        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, array);
        spi.setAdapter(arrayAdapter);
        spi.setOnItemSelectedListener(new ItemClick());
    }
    @Override
    public void onFail(String msg) {
        Toast.makeText(SexUpdateActivity.this,"性别列表获取失败",Toast.LENGTH_SHORT).show();
    }
};
    //对条目的点击
    private class ItemClick implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View arg1, int arg2,
                                   long arg3) {
            updateLaterSex=(String) parent.getItemAtPosition(arg2);
            Log.e("TAG", "updatelateclub"+updateLaterSex);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
    public String getUser_id() {
        SharedPreferences sp = SexUpdateActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
}

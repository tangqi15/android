package com.example.group.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Activity.MineActivityActivity;
import com.example.group.Activity.MineCollectionActivity;
import com.example.group.Activity.MineFindActivity;
import com.example.group.Activity.MineGroupActivity;
import com.example.group.Activity.MoreSettingActivity;
import com.example.group.Activity.UserHeadPicActivity;
import com.example.group.Activity.UserInfoActivity;
import com.example.group.Bean.UserBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.MineModel;
import com.example.group.R;
import com.example.group.Server.Server;
import com.squareup.picasso.Picasso;

public class FragmentMine extends Fragment {
    View view = null;
    ImageView img_mine_user_head;
    LinearLayout LineUserInfo;
    TextView tv_mine_user_screen;
    TextView tv_mine_user_username;
    TextView mine_collect;
    TextView mine_activity;
    TextView mine_group;
    TextView mine_more;
    //我的收藏
    LinearLayout ly_mine_collection;
    //我的活动
    LinearLayout ly_mine_activity;
    //我的社团
    LinearLayout ly_mine_group;
    //我的发现
    LinearLayout ly_mine_find;
    //头像
    String head_portrait;
    //账号
    String username;
    //网名
    String nickname;
    //性别
    String sex;
    //用户id
    String user_id;
    //绑定的手机号
    String phone;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_fragment,container,false);
        initView();
        initSend();
        initEvent();

        return view;
    }

    private void initView() {
        img_mine_user_head = view.findViewById(R.id.img_mine_user_head);
        LineUserInfo = view.findViewById(R.id.LineUserInfo);
        tv_mine_user_screen = view.findViewById(R.id.tv_mine_user_screen);
        tv_mine_user_username = view.findViewById(R.id.tv_mine_user_username);
        mine_collect = view.findViewById(R.id.mine_collect);
        mine_activity = view.findViewById(R.id.mine_activity);
        mine_group = view.findViewById(R.id.mine_group);
        mine_more = view.findViewById(R.id.mine_more);
        ly_mine_collection = view.findViewById(R.id.ly_mine_collection);
        ly_mine_activity = view.findViewById(R.id.ly_mine_activity);
        ly_mine_group = view.findViewById(R.id.ly_mine_group);
        ly_mine_find = view.findViewById(R.id.ly_mine_find);
    }

    private void initEvent() {

        //个人头像   （显示大头像）
        img_mine_user_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserHeadPicActivity.class);
                intent.putExtra("head_portrait",head_portrait);
                startActivity(intent);
            }
        });
        //个人信息
        LineUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserInfoActivity.class);
                intent.putExtra("head_portrait",head_portrait);
                intent.putExtra("username",username);
                intent.putExtra("sex",sex);
                intent.putExtra("user_id",user_id);
                intent.putExtra("nickname",nickname);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });
        //我的收藏
        ly_mine_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MineCollectionActivity.class);
                startActivity(intent);
            }
        });
        //我的活动
        ly_mine_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MineActivityActivity.class);
                startActivity(intent);
            }
        });
        //我的社团
        ly_mine_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MineGroupActivity.class);
                startActivity(intent);
            }
        });
        //我的发现
        ly_mine_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MineFindActivity.class);
                startActivity(intent);
            }
        });
        //更多
        mine_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MoreSettingActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initSend() {
        String user_id = getUser_id();
        MineModel mineModel = new MineModel();
        mineModel.getUserInfo(user_id,tListener);
    }
    public String getUser_id() {
        SharedPreferences sp = getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
    TListener<UserBean> tListener = new TListener<UserBean>() {
        @Override
        public void onResponse(UserBean userBean) {
            //头像
            head_portrait = userBean.getUser_pic();
            //账号
            username = userBean.getUsername();
            //性别
            sex = userBean.getSex_name();
            //昵称
            nickname = userBean.getScreen();
            //手机号
            phone = userBean.getPhone();
            user_id = userBean.getUser_id();
            if ("".equals(head_portrait)){

            }else{
                Picasso.with(getContext()).load(Server.BASE_URL+"neu_soft_group/public/static/user_head/"+head_portrait)
                        .resize(200,200).into(img_mine_user_head);
            }
            nickname = userBean.getScreen();
            tv_mine_user_screen.setText("     "+nickname);
            tv_mine_user_username.setText("     账号："+username);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(getContext(),"并没有数据返回",Toast.LENGTH_SHORT).show();
        }
    };

}

package com.example.group.Activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.group.Fragment.FragmentEvent;
import com.example.group.Fragment.FragmentGroup;
import com.example.group.Fragment.FragmentFind;
import com.example.group.Fragment.FragmentMine;
import com.example.group.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {
LinearLayout ly_group,ly_event,ly_message,ly_mine;
ImageView img_group,img_event,img_message,img_mine;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
Fragment fragment_group,fragment_event,fragment_find,fragment_mine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initBound();
    }


    void initView() {
        img_group = findViewById(R.id.img_group);
        img_event = findViewById(R.id.img_event);
        img_message = findViewById(R.id.img_message);
        img_mine = findViewById(R.id.img_mine);
        ly_group = findViewById(R.id.ly_group);
        ly_event = findViewById(R.id.ly_event);
        ly_message = findViewById(R.id.ly_message);
        ly_mine = findViewById(R.id.ly_mine);
        selectTab(0);
    }
private void selectTab(int i){
        //从Fragment管理器中得到Fragment当前已加入Fragment回退栈中的Fragment的数量
        fragmentManager = getSupportFragmentManager();
        //实例化一个fragment事务管理器
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (i){
            case 0:
                if (fragment_group==null){
                    fragment_group = new FragmentGroup();
                    fragmentTransaction.add(R.id.fragelayout,fragment_group);
                }else{
                    fragmentTransaction.show(fragment_group);
                }
                img_group.setImageResource(R.drawable.ic_group_green);
                break;
            case 1:
                img_event.setImageResource(R.drawable.ic_event_green);
                if (fragment_event==null){
                    fragment_event = new FragmentEvent();
                    fragmentTransaction.add(R.id.fragelayout,fragment_event);
                }else{
                    fragmentTransaction.show(fragment_event);
                }

                break;
            case 2:
                if (fragment_find==null){
                    fragment_find = new FragmentFind();
                    fragmentTransaction.add(R.id.fragelayout,fragment_find);
                }else{
                    fragmentTransaction.show(fragment_find);
                }
                img_message.setImageResource(R.drawable.ic_find_green);
                break;
            case 3:
             /*   if (fragment_mine==null){*/
                    fragment_mine = new FragmentMine();
                    fragmentTransaction.add(R.id.fragelayout,fragment_mine);
               /* }else{
                    fragmentTransaction.show(fragment_mine);
                }*/
                img_mine.setImageResource(R.drawable.ic_mine_green);
                break;
        }
    fragmentTransaction.commit();
}
private void hideFragment(FragmentTransaction fragmentTransaction){
        if (fragment_group!=null){
            fragmentTransaction.hide(fragment_group);
        }
        if (fragment_event!=null){
            fragmentTransaction.hide(fragment_event);
        }
        if(fragment_find!=null){
            fragmentTransaction.hide(fragment_find);
        }
        if(fragment_mine!=null){
            fragmentTransaction.hide(fragment_mine);
        }
}

    void initEvent() {
        ly_group.setOnClickListener(this);
        ly_event.setOnClickListener(this);
        ly_message.setOnClickListener(this);
        ly_mine.setOnClickListener(this);
    }


    void initBound() {

    }

    @Override
    public void onClick(View v) {
        selectImage();
        switch (v.getId()){
            case R.id.ly_group:
                 selectTab(0);
                 break;
            case R.id.ly_event:
                selectTab(1);
                break;
            case R.id.ly_message:
                selectTab(2);
                break;
            case R.id.ly_mine:
                selectTab(3);
                break;
        }
    }
    private void selectImage(){
        img_group.setImageResource(R.drawable.ic_group);
        img_event.setImageResource(R.drawable.ic_event);
        img_message.setImageResource(R.drawable.ic_find);
        img_mine.setImageResource(R.drawable.ic_mine);
    }
}

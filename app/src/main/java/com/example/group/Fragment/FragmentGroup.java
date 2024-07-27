package com.example.group.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.group.Activity.SearchGroupActivty;
import com.example.group.Adapter.GroupAdapter;
import com.example.group.Adapter.ViewPagerAdapter;
import com.example.group.Bean.AllGroupBean;
import com.example.group.Listener.ListListener;
import com.example.group.Model.GroupModel;
import com.example.group.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentGroup extends Fragment {
    View view=null;

    TextView tv_type;
    RecyclerView recycle_group;
    GroupAdapter groupAdapter;
    private List<AllGroupBean> items;
    LinearLayout ly_search_group;

    private ViewPager viewPager;
    // 数据源
    private int[] images = { R.drawable.view1, R.drawable.view2,
            R.drawable.view3 };
    // 存放数据源的集合
    List<ImageView> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.group_fragment,container,false);
        initSend();
        initView();
        initPic();
        initEvent();
        return view;
    }

    private void initSend() {
        GroupModel groupModel = new GroupModel();
        groupModel.getGroupList(listlistener);
    }

    private void initView(){
       // tv_type = view.findViewById(R.id.tv_type);
        recycle_group = view.findViewById(R.id.recycle_group);
        ly_search_group = view.findViewById(R.id.ly_search_group);

      /*  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,cities );
        adapter.setDropDownViewResource(android.R.layout. simple_spinner_dropdown_item );
        spinner = (Spinner) this .view.findViewById(R.id. Spinner01 );
        spinner .setAdapter(adapter);*/

        //滑动组件
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_group.setLayoutManager(linearLayoutManager);
        groupAdapter = new GroupAdapter(getActivity(),items,R.layout.group_item);
        recycle_group.setAdapter(groupAdapter);
        recycle_group.setItemAnimator(new DefaultItemAnimator());

    }

    private void initPic() {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        // 实例化list
        list = new ArrayList<ImageView>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(getContext());
            //解决viewpager的图片占满整个控件，  setImageResource 更换为  setBackgroundResource的方法获取图片资源
            imageView.setBackgroundResource(images[i]);
            list.add(imageView);
        }
        // 绑定适配器
        viewPager.setAdapter(new ViewPagerAdapter(getContext(), list));
        // 当有3张图片时,走到id=2时最后一张,
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2
                % list.size());// 假设viewPager有无数条数据

        // 延迟两秒发送一条消息
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    // 让图片自动播放,无限循环
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            /* 无限循环播放,每次都把所有相片,放到后面查看*/
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            sendEmptyMessageDelayed(1, 2000);
        }
    };
        private void initEvent(){
            ly_search_group.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), SearchGroupActivty.class);
                    getContext().startActivity(intent);
                }
            });
        }


         ListListener<AllGroupBean> listlistener = new ListListener<AllGroupBean>() {
             @Override
             public void onResponse(List<AllGroupBean> list) {
                 groupAdapter.setList(list);
             }

             @Override
             public void onFail(String msg) {
                Log.e("Tag","这是访问失败了么");
             }
         };
}

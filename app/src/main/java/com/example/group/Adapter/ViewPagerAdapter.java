package com.example.group.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;
//轮播图
public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<ImageView> list;

    public ViewPagerAdapter(Context context, List<ImageView> list) {
        super();
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Integer.MAX_VALUE;//设置viewPager里有N条数据
        //return list.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //假设有3张照片,走到id=2时,走id=0的,所以取余
        int size = list.size();
       // container.addView(list.get(position%size));
        Log.e("Tag", "postion"+position);
        int sum = position%size;
        Log.e("Tag", "sum"+sum);

        container.addView(list.get(sum));

        return list.get(position%size);
        //return list.get(position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView(list.get(position%list.size()));
        //container.removeView(list.get(position));
        //super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0==arg1;
    }

}

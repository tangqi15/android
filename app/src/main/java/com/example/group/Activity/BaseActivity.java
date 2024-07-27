package com.example.group.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        AtyContainer.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity&从栈中移除该Activity
        AtyContainer.getInstance().removeActivity(this);
    }

}

class AtyContainer {

    public AtyContainer() {
    }

    private static AtyContainer instance = new AtyContainer();
    private static List<Activity> activityStack = new ArrayList<Activity>();


    public static AtyContainer getInstance() {
        return instance;
    }

    public void addActivity(Activity aty) {
        activityStack.add(aty);
    }

    public void removeActivity(Activity aty) {
        activityStack.remove(aty);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}
/*---------------------
        作者：一点一滴的积累
        来源：CSDN
        原文：https://blog.csdn.net/qq_31939617/article/details/80755849
        版权声明：本文为博主原创文章，转载请附上博文链接！*/


package com.example.group.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Adapter.CommentListAdapter;
import com.example.group.Adapter.FindItemAdapter;
import com.example.group.Adapter.FindItemAdapter1;
import com.example.group.Adapter.FindItemAdapter2;
import com.example.group.Bean.CommentListBean;
import com.example.group.Bean.FindBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Bean.UserBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Model.FindModel;
import com.example.group.Model.PublicCommentModel;
import com.example.group.R;
import com.example.group.Server.Server;
import com.example.group.util.FullyGridLayoutManager;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class FindItemDetailsActivity extends BaseActivity {
    ImageView img_findItem_back;
    ImageView img_find_details_head;
    TextView tv_find_item_details_screen,tv_find_item_details_conent,tv_find_item_details_time;
    EditText edt_comment_content;
    RecyclerView recycle_find_item_details_point_recycle,recycle_comment_details;
    Button btn_publish_comment;
    List<String> itemItems;


    String user_id;
    String find_id;
    PublicCommentModel publicCommentModel = new PublicCommentModel();
    CommentListAdapter commentListAdapter;
    List<CommentListBean> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finditem_details_activiaty);
        initView();
        initEvent();
        initHeadBand();
    }

    private void initHeadBand() {
        FindModel findModel = new FindModel();
        findModel.getFindDetailsHead1(user_id,tListener1);
        findModel.getFindDetailsHead2(find_id,tListener2);
    }
    TListener<UserBean> tListener1  = new TListener<UserBean>() {
        @Override
        public void onResponse(UserBean userBean) {

            String user_screen_name = userBean.getScreen();
            String user_pic = userBean.getUser_pic();
            tv_find_item_details_screen.setText(user_screen_name);
            Picasso.with(FindItemDetailsActivity.this).
                    load(Server.BASE_URL+"neu_soft_group/public/static/user_head/"+user_pic).
                    into(img_find_details_head);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(FindItemDetailsActivity.this,"1错误，请检查网络是否连接",Toast.LENGTH_SHORT).show();
        }
    };
    TListener<FindBean> tListener2  = new TListener<FindBean>() {
        @Override
        public void onResponse(FindBean findBean) {
            String find_intro = findBean.getFind_intro();
            String find_pic = findBean.getFind_pic();
            String find_addtime = findBean.getFind_addtime();
            tv_find_item_details_conent.setText(find_intro);
            tv_find_item_details_time.setText(find_addtime);


            itemItems = Arrays.asList(find_pic.split("\\.jpg"));
            int a  = itemItems.size();
            if(a == 0 ){
                initPic1();
            }if(a == 1){
                initPic1();
            }if(a == 2){
                initPic2();
            }if (a > 2){
                initPic3();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(FindItemDetailsActivity.this,"2错误，请检查网络是否连接",Toast.LENGTH_SHORT).show();
        }
    };
    private void initView() {
        img_findItem_back = findViewById(R.id.img_findItem_back);
        img_find_details_head = findViewById(R.id.img_find_details_head);
        tv_find_item_details_screen = findViewById(R.id.tv_find_item_details_screen);
        tv_find_item_details_conent = findViewById(R.id.tv_find_item_details_conent);
        tv_find_item_details_time = findViewById(R.id.tv_find_item_details_time);
        recycle_find_item_details_point_recycle = findViewById(R.id.recycle_find_item_details_point_recycle);
        recycle_comment_details = findViewById(R.id.recycle_comment_details);
        edt_comment_content = findViewById(R.id.edt_comment_content);
        btn_publish_comment = findViewById(R.id.btn_publish_comment);


        user_id = getIntent().getStringExtra("user_id");
        find_id = getIntent().getStringExtra("find_id");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //设置为线性布局
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器
        commentListAdapter = new CommentListAdapter(this,items,R.layout.comment_item);
        //设置adapter
        recycle_comment_details.setAdapter(commentListAdapter);
        //设置增加或删除条目的动画
        recycle_comment_details.setItemAnimator(new DefaultItemAnimator());
        recycle_comment_details.setLayoutManager(linearLayoutManager);

        publicCommentModel.getCommentList(find_id,listenListener);
    }
    public String getUser_id() {
        SharedPreferences sp = FindItemDetailsActivity.this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id","");
    }
    private void initPic1() {
        FullyGridLayoutManager manager = new
                FullyGridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recycle_find_item_details_point_recycle.setLayoutManager(manager);
        //itemitems表示数组长度
        FindItemAdapter1 findItemAdapter1 = new FindItemAdapter1(this,itemItems,R.layout.find_item_imgae1);
        recycle_find_item_details_point_recycle.setAdapter(findItemAdapter1);
    }
    private void initPic2() {
        FullyGridLayoutManager manager = new
                FullyGridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        recycle_find_item_details_point_recycle.setLayoutManager(manager);
        //itemitems表示数组长度
        FindItemAdapter2 findItemAdapter2 = new FindItemAdapter2(this,itemItems,R.layout.find_item_imgae2);
        recycle_find_item_details_point_recycle.setAdapter(findItemAdapter2);
    }
    private void initPic3() {
        FullyGridLayoutManager manager = new
                FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);

        recycle_find_item_details_point_recycle.setLayoutManager(manager);
        //itemitems表示数组长度
        FindItemAdapter findItemAdapter = new FindItemAdapter(this,itemItems,R.layout.find_item_imgae3);
        recycle_find_item_details_point_recycle.setAdapter(findItemAdapter);


    }
    private void initEvent() {
        img_findItem_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_publish_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id = getUser_id();

                publicCommentModel.getPublicCommentMessage(find_id,user_id,
                                edt_comment_content.getText().toString(),tListener);
            }
        });
        img_find_details_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindItemDetailsActivity.this,PersonPublicActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
    }
    TListener<SuccessBean> tListener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            String success = successBean.getSuccess();
            if (success.equals("1")){
                Toast.makeText(FindItemDetailsActivity.this,"发布评论成功",Toast.LENGTH_SHORT).show();
                publicCommentModel.getCommentList(find_id,listenListener);
            }else{
                Toast.makeText(FindItemDetailsActivity.this,"发布评论失败",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(FindItemDetailsActivity.this,"发布评论失败，请检查网络是否连接",Toast.LENGTH_SHORT).show();

        }
    };
    ListListener<CommentListBean> listenListener = new ListListener<CommentListBean>() {
        @Override
        public void onResponse(List<CommentListBean> list) {
            commentListAdapter.setList(list);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(FindItemDetailsActivity.this,"获取评论列表失败，请检查网络是否连接",Toast.LENGTH_SHORT).show();

        }
    };

}

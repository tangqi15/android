package com.example.group.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.UserReportModel;
import com.example.group.R;
import com.example.group.util.FullyGridLayoutManager;
import com.example.group.util.GridImageAdapter;
import com.example.group.util.UploadUtilNum;
import com.example.group.util.UploadUtilNumReport;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserReportActivity extends BaseActivity {
private RecyclerView recycle_report_pic;
    //图片列表
    List<String> list_pic = new ArrayList();
    //上传图片类，需要修改，文件夹修改成举报的文件夹
    UploadUtilNumReport uploadUtilNumReport =  new UploadUtilNumReport();

    private int maxSelectNum = 9;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private PopupWindow pop;
    private Button btn_report_submit;
    private EditText edt_report_content;
    private ImageView img_user_report_back;
    //被举报人ID
    private String user_id;
    //举报人的ID
    String user_id_report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report);
        initView();
        initWidget();
        initEvent();
    }

    private void initView() {
        recycle_report_pic = findViewById(R.id.recycle_report_pic);
        btn_report_submit = findViewById(R.id.btn_report_submit);
        edt_report_content = findViewById(R.id.edt_report_content);
        img_user_report_back = findViewById(R.id.img_user_report_back);
        user_id = getIntent().getStringExtra("user_id");
    }

    private void initEvent() {
        btn_report_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i =  list_pic.size();
                List<String> listfilename = new ArrayList<String>();
                Log.e("Tag","list1"+list_pic);
                for (int j = 0;j<i;j++){
                    String path = list_pic.get(j);
                    Log.e("Tag","list1"+list_pic.get(j));
                    uploadUtilNumReport.upload(path);
                    Log.e("Tag","list1"+""+list_pic.get(j)+"");

                    File ff = new File(path);
                    String fname =  ff.getName();
                    listfilename.add(fname);
                }
                //集合转字符串  用拼接的方式
                String report_picture = "";
                for (int m=0;m<listfilename.size();m++){
                    report_picture = report_picture+listfilename.get(m);
                }
                String report_content = edt_report_content.getText().toString();

                UserReportModel userReportModel = new UserReportModel();
                //判断有没有文字
                if("".equals(report_content)){
                    Toast.makeText(UserReportActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    //举报者的用户id
                    String user_id_report = getuid();
                    userReportModel.getReportSubmitMessage(
                            user_id,user_id_report,report_picture,report_content,listener);
                }
            }
        });
        img_user_report_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recycle_report_pic.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recycle_report_pic.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(UserReportActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(UserReportActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(UserReportActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }
    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @Override
        public void onAddPicClick() {
            //第一种方式，弹出选择和拍照的dialog
            showPop();
        }
    };

    private void showPop() {
        View bottomView = View.inflate(UserReportActivity.this, R.layout.layout_bottom_dialog2, null);
        TextView mAlbum = (TextView) bottomView.findViewById(R.id.tv_album);

        TextView mCancel = (TextView) bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(UserReportActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;

                    case R.id.tv_cancel:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);
                    Log.e("Tag","图片列表"+images);
//                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    list_pic =  adapter.getList1();
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }
    TListener<SuccessBean> listener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            String a = successBean.getSuccess();
            if (a.equals("1")){
                Toast.makeText(UserReportActivity.this,"举报成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserReportActivity.this,Main3Activity.class);
                startActivity(intent);
            }else{
                Toast.makeText(UserReportActivity.this,"举报失败1",Toast.LENGTH_SHORT).show();
               // Toast.makeText(UserReportActivity.this,"用户id"+user_id,Toast.LENGTH_SHORT).show();
               // Toast.makeText(UserReportActivity.this,"举报者id"+user_id_report,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(UserReportActivity.this,"举报失败",Toast.LENGTH_SHORT).show();
        }
    };
    private String getuid() {
        SharedPreferences sp =getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id", "");
    }
}

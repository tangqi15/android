package com.example.group.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Bean.FindTypeBean;
import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.ListListener;
import com.example.group.Listener.TListener;
import com.example.group.Model.FindSubmitModel;
import com.example.group.Model.FindTypeModel;
import com.example.group.R;
import com.example.group.util.FullyGridLayoutManager;
import com.example.group.util.GridImageAdapter;
import com.example.group.util.UploadUtilNum;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindSubmitActivity extends BaseActivity {
    TextView tv_cancel;
    private int maxSelectNum = 9;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private RecyclerView recycler;
    private PopupWindow pop;
    TextView tv_release;
    EditText tv_point_intro;
    //图片列表
    List<String> list_pic = new ArrayList();
    UploadUtilNum uploadUtilNum =  new UploadUtilNum();
    String user_id;
    //选择类型
    Spinner spi2;
    //定义一个数组   用来存放     类型列表
    String[] array;
    //  定义一个
    ArrayAdapter<String> arrayAdapter;

    // 定义变量  存放   要更新的  类型
    String find_type;
    private String getuid() {
        SharedPreferences sp =getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        return sp.getString("user_id", "");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_submit);
        initView();
        initWidget();
        initEvent();
    }
    private void initView() {
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_release = findViewById(R.id.tv_release);
        tv_point_intro = findViewById(R.id.tv_point_intro);
        recycler = findViewById(R.id.recycler);
        spi2 = findViewById(R.id.spi2);
        user_id = getuid();
        //发送请求   获取数据库  中的 发现类别
        FindTypeModel findTypeModel = new FindTypeModel();
        findTypeModel.getFindTypeList(findTypeListListener);
    }
    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recycler.setAdapter(adapter);
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
                            PictureSelector.create(FindSubmitActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(FindSubmitActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(FindSubmitActivity.this).externalPictureAudio(media.getPath());
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
        View bottomView = View.inflate(FindSubmitActivity.this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = (TextView) bottomView.findViewById(R.id.tv_album);
        TextView mCamera = (TextView) bottomView.findViewById(R.id.tv_camera);
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
                        PictureSelector.create(FindSubmitActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(FindSubmitActivity.this)
                                .openCamera(PictureMimeType.ofImage())
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
        mCamera.setOnClickListener(clickListener);
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
    private void initEvent() {
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //发布
        tv_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i =  list_pic.size();
                List<String> listfilename = new ArrayList<String>();
                Log.e("Tag","list1"+list_pic);
                for (int j = 0;j<i;j++){
                    String path = list_pic.get(j);
                    Log.e("Tag","list1"+list_pic.get(j));
                    uploadUtilNum.upload(path);
                    Log.e("Tag","list1"+""+list_pic.get(j)+"");

                    File ff = new File(path);
                    String fname =  ff.getName();
                    listfilename.add(fname);
                }
                //集合转字符串  用拼接的方式
                String point_picture = "";
                for (int m=0;m<listfilename.size();m++){
                    point_picture = point_picture+listfilename.get(m);
                }
                String point_intro = tv_point_intro.getText().toString();

                FindSubmitModel findSubmitModel = new FindSubmitModel();
                //判断有没有文字
                if("".equals(point_intro)){
                 Toast.makeText(FindSubmitActivity.this,"内容不能为空",Toast.LENGTH_SHORT).show();
                }else{
                    findSubmitModel.getFindSubmitMessage(
                            user_id,point_picture,point_intro,find_type,listener);
                }

            }
        });
    }

    TListener<SuccessBean> listener = new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            String a = successBean.getSuccess();
            if (a.equals("1")){
                Toast.makeText(FindSubmitActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FindSubmitActivity.this,Main3Activity.class);
                startActivity(intent);
            }else{
                Toast.makeText(FindSubmitActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(FindSubmitActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
        }
    };

    //获取  发现的  类别             存到一个  List  中
    ListListener<FindTypeBean> findTypeListListener = new ListListener<FindTypeBean>() {
        @Override
        public void onResponse(List<FindTypeBean> list) {
            int i=list.size();
            String[] str = new String[i];
            ArrayList<String> list1=new ArrayList<String>();
            for(int j=0;j<i;j++){
                String findTypeName =  list.get(j).getFind_type_name();
               list1.add(findTypeName);

            }
            array = list1.toArray(new String[list1.size()]);
            arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, array);
            spi2.setAdapter(arrayAdapter);
            spi2.setOnItemSelectedListener(new ItemClick());

        }

        @Override
        public void onFail(String msg) {

        }
    };

    //对条目的点击
    private class ItemClick implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View arg1, int arg2,
                                   long arg3) {
            find_type=(String) parent.getItemAtPosition(arg2);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
}



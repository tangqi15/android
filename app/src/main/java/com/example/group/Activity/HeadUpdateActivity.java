package com.example.group.Activity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.group.Bean.SuccessBean;
import com.example.group.Listener.TListener;
import com.example.group.Model.SendPicModel;
import com.example.group.R;
import com.example.group.Server.Server;
import com.example.group.util.BitmapOption;
import com.example.group.util.UploadUtil;
import com.squareup.picasso.Picasso;

import java.io.File;

//更换头像
public class HeadUpdateActivity extends BaseActivity implements View.OnClickListener{
    ImageView img_back,img_head_update;
    Button btn_save;
    String uid;
    String head_portrait="";
    Bitmap bitmap;

    //图片绝对路径
    String path="";
    private PopupWindow mpopupWindow;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private String uploadUrl = "http://10.0.2.2/neu_soft_group/public/upload.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_update);
        initView();
        initEvent();
    }



    private void initView() {
        img_back = findViewById(R.id.img_head_update_back);
        img_head_update = findViewById(R.id.img_head_update);
        btn_save = findViewById(R.id.btn_save);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_head_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
        //把上一个活动界面跳转时候 传过来的 活动id 取出来
        uid = getIntent().getStringExtra("user_id");
        head_portrait = getIntent().getStringExtra("head_portrait");

        if ("".equals(head_portrait)){

        }else{
            Picasso.with(
                    HeadUpdateActivity.this)
                    .load(Server.BASE_URL+"neu_soft_group/public/static/user_head/"+head_portrait)
                    .resize(300,300).into(img_head_update);
        }


    }
    private void initEvent() {
        //修改头像按钮

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(path)){

                    Toast.makeText(HeadUpdateActivity.this,"请选择头像",Toast.LENGTH_SHORT).show();

                }else{
                    UploadUtil uploadUtil = new UploadUtil();
                    uploadUtil.upload(path);
                    // if ("1".equals(uploadUtil.callUtil)){
                    File ff = new File(path);
                    String fname =  ff.getName();
                    Log.e("Tag","文件名称"+fname);

                    SendPicModel sendPicModel = new SendPicModel();
                    sendPicModel.getPic(uid,fname,tListener);
                    //  }else{
                    //        Toast.makeText(HeadUpdateActivity.this,"上传图片失败",Toast.LENGTH_SHORT).show();
                    //  }

                }



            }
        });
    }

    TListener<SuccessBean> tListener =new TListener<SuccessBean>() {
        @Override
        public void onResponse(SuccessBean successBean) {
            Toast.makeText(HeadUpdateActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(HeadUpdateActivity.this,Main2Activity.class);
            startActivity(intent);
        }

        @Override
        public void onFail(String msg) {
            Toast.makeText(HeadUpdateActivity.this,"修改失败,请稍微再试",Toast.LENGTH_SHORT).show();
        }
    };

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(HeadUpdateActivity.this).inflate(R.layout.layout_bottom_dialog,null);
        mpopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        mpopupWindow.setContentView(contentView);
        //设置各个控件的点击响应
        TextView mAlbum = (TextView) contentView.findViewById(R.id.tv_album);
        TextView mCamera = (TextView) contentView.findViewById(R.id.tv_camera);
        TextView mCancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        mAlbum.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        //显示popupwindow
        View rootview = LayoutInflater.from(HeadUpdateActivity.this).inflate(R.layout.layout_bottom_dialog,null);
        mpopupWindow.showAtLocation(rootview, Gravity.BOTTOM,0,0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tv_album: {
                //在这里跳转到手机系统相册里面
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //设置请求码  100
                startActivityForResult(intent,100);
                break;
            }
            case R.id.tv_camera:{
                //调用自带的照相机
                Intent intent1 =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                tempUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                startActivityForResult(intent1, 200);

                break;
            }
            case R.id.tv_cancel:{
                mpopupWindow.dismiss();
                break;
            }
        }
        closePopupWindow();

    }
    public void closePopupWindow() {
        if (mpopupWindow != null && mpopupWindow.isShowing()) {
            mpopupWindow.dismiss();
            mpopupWindow = null;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        switch (requestCode) {
            case 100://这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        //文件队列
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                //从系统表中查询指定Uri对应的照片
                                filePathColumn, null, null, null);
                        //moveToFirst() 把光标移动到第一行
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        //字符串类型
                        path = cursor.getString(columnIndex);  //获取照片路径
                        path = cursor.getString(columnIndex);  //获取照片路径
                        Log.e("Tag","图片的路径"+path);
                        cursor.close();
                        //开启   权限   访问图片的库的时候  android 6.0 以上需要 开启  权限
                   if (ActivityCompat.checkSelfPermission(HeadUpdateActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(HeadUpdateActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                        }
                       //转换成  andorid用的路径
                       // 返回结果是解码的位图，或者如果不能解码则返回空。
                       //从文件中解码生成一个位图。如果支付的文件名为空，或者不能解码出一个位图，方法将返回空。
                       // 字符串类型  转换成 Bitmap
                       bitmap = BitmapFactory.decodeFile(path);
                       Log.e("Tag","图片的路径2"+bitmap);
                       //ImageView的setImageBitmap()是设置imageView组件的图片显示  类型是 Bitmap
                       img_head_update.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        // TODO Auto-generatedcatch block
                        e.printStackTrace();
                    }
                }
                break;
            case 200:
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    Bundle extras = data.getExtras();
                    Bitmap photoBit = (Bitmap) extras.get("data");
                    Bitmap option = BitmapOption.bitmapOption(photoBit, 5);

                    img_head_update.setImageBitmap(option);
                }
                break;
        }
    }



}

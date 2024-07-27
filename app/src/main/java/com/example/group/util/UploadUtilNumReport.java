package com.example.group.util;

import android.util.Log;

import com.example.group.Server.Server;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class UploadUtilNumReport {

    private String uploadUrlNum = Server.BASE_URL+"neu_soft_group/public/uploadreport.php";
    AsyncHttpClient client = new AsyncHttpClient();
    RequestParams params = new RequestParams();



    //添加文件
    public void upload(String filepath){
        File f = new File(filepath);
        if(f.exists()){
            Log.i("AsyncHttp", "Yes") ;
            try {
                params.put("myFile", f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            Log.i("AsyncHttp", "No") ;
        }

        //把文件上传
        client.post(uploadUrlNum, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                try {
                    //获取返回内容
                    String resp = new String(bytes, "utf-8");
                    Log.i("AsyncHttp", resp) ;
                    //在这里处理返回的内容，例如解析json什么的...


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //在这里处理连接失败的处理...
                Log.i("AsyncHttp", "失败") ;
            }


        });



    }






}

package com.DC.android.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.DC.android.LoginActivity;
import com.DC.android.MainActivity;
import com.DC.android.PlanActivity;
import com.DC.android.ProjectActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by XZJ on 2017/11/7.
 */

public class UploadUtil {
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private  static final OkHttpClient client = new OkHttpClient();
    private static boolean boo;
    public static boolean uploadImg(String mImgUrls, String token, String newProjectId) {
        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

            File f=new File(mImgUrls);
            if (f!=null) {
                builder.addFormDataPart("pic",f.getName(),RequestBody.create(MEDIA_TYPE_PNG, f));
            }

        //添加其它信息
           builder.addFormDataPart("token",token);
        builder.addFormDataPart("newProjectId",newProjectId);
//        builder.addFormDataPart("mapX", SharedInfoUtils.getLongitude());
//        builder.addFormDataPart("mapY",SharedInfoUtils.getLatitude());
//        builder.addFormDataPart("name",SharedInfoUtils.getUserName());


        final MultipartBody requestBody = builder.build();
        //构建请求
        Request request = new Request.Builder()
                .url("http://139.199.162.139:8080/user/project-img-upload")//地址
                .post(requestBody)//添加请求体
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("MainActivity",e.getLocalizedMessage());
                System.out.println("上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("upPic", response.body().toString());
            //    System.out.println("上传照片成功：response = " + response.body().string());
                String responseText=response.body().string();
                String ok="";
                try {
                    JSONObject jsonObject = new JSONObject(responseText);
                     ok = jsonObject.getString("ok");
                  //  reason=jsonObject.getString("reason");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                        if("true".equals(ok)) boo=true;
                        else boo=false;

                        //  show();




            }
        });
        return  boo;
    }

}

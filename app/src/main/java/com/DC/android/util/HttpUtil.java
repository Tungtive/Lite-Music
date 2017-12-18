package com.DC.android.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by XZJ on 2017/10/18.
 */

public class HttpUtil {
    public static void  sendOkHttpRequest(String address,okhttp3.Callback callback){
      OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .build();
        Request request =new Request.Builder().url(address).addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate")
                .addHeader("Accept-Language","zh-CN,zh;q=0.9")
                .addHeader("Cache-Control","max-age=0")
                .addHeader("Connection","keep-alive")
                .addHeader("Cookie","__remember_me=true; MUSIC_U=214255217e6371e2339b20098bad0c415c9af111578fdd138b6c9a2b59a25e6db199820a2971080c198009e6f9623b063e0efb8291343131; __csrf=38277ad66d4831ee2494facbcde474a0")
                .addHeader("Host","118.89.20.206")
                .addHeader("If-None-Match","W/\"d494-VUeyDERJEWz/qWo4KRYqpkjUJws\"")
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Mobile Safari/537.36")
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void  sendDonateOkHttpRequest(String address,String token,int projectId,String message ,String numofMoney,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token",token ).add("projectId",Integer.toString(projectId)).add("numOfMoney",numofMoney).add("message",message)
                .build();
        Request request =new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }
    public static void  sendPlanSignOkHttpRequest(String address,String token,int planId,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token",token ).add("planId",Integer.toString(planId))
                .build();
        Request request =new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }
    public static void  sendPlanAddOkHttpRequest(String address,String token,String planName,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token",token ).add("planName",planName)
                .build();
        Request request =new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }
    public static void  sendPlanDeleteOkHttpRequest(String address,String token,int planId,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token",token ).add("planId",Integer.toString(planId))
                .build();
        Request request =new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }
    public static void  sendProjectOkHttpRequest(String address,String token,int projectId,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token",token ).add("projectId",Integer.toString(projectId))
                .build();
        Request request =new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }
    public static void  sendAddProjectOkHttpRequest(String address,String token,String  projectName,String initiatorName,String description,String targetMoney,String detail, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("token",token ).add("projectName",projectName).add("initiatorName",initiatorName).add("description",description).add("targetMoney",targetMoney).add("detail",detail)
                .build();
        Request request =new Request.Builder().url(address).post(requestBody).build();
        client.newCall(request).enqueue(callback);
    }

}

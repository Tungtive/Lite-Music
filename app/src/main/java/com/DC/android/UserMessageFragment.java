package com.DC.android;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
=======
import android.widget.LinearLayout;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
import android.widget.Toast;

import com.DC.android.db.Plan;
import com.DC.android.db.UserMessage;
import com.DC.android.util.HttpUtil;
import com.DC.android.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by XZJ on 2017/11/11.
 */

public class UserMessageFragment extends android.support.v4.app.Fragment {
<<<<<<< HEAD
    private List<UserMessage> userMessagesList = new ArrayList<UserMessage>();
    private RecyclerView recyclerView;
    WebView mWebView;
    WebSettings mWebSettings;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_message, container, false);
        mWebView =(WebView)view.findViewById(R.id.webView1);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.zfltest.com/#/searchSong");
        mWebView.setWebViewClient(new HelloWebViewClient ());
        final MainActivity activity = (MainActivity) getActivity();
        activity.toolbar.setTitle("搜索");
        return view;
    }
//Web视图  
        private class HelloWebViewClient extends WebViewClient {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view,String url){
                       view.loadUrl(url);
                       return true;
                }
        }
}
=======
     private List<UserMessage> userMessagesList= new ArrayList<UserMessage>();
    private   RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_message,container,false);
        initMessage(userMessagesList);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        return view;
    }
    private void initMessage(List<UserMessage>List){

        String Url="http://139.199.162.139:8080/user/get-user-message";

        MainActivity activity=(MainActivity)getActivity();
        HttpUtil.sendOkHttpRequest(Url, activity.TOKEN,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if  (getActivity() == null) return;  //getActivity()有可能为空 防止空指针异常
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  closeProgressDialog();
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                        //  show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText =response.body().string();
                JSONObject jsonObject=new JSONObject();
                Log.i("MainActivity",responseText);
                boolean result=  Utility.handleMessageResponce(responseText);
                if  (getActivity() == null) return;  //getActivity()有可能为空 防止空指针异常
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        userMessagesList= DataSupport.findAll(UserMessage.class);
                        LinearLayoutManager layoutManager = new  LinearLayoutManager(getContext());
                        layoutManager.setStackFromEnd(true);
                        layoutManager.setReverseLayout(true);
                        recyclerView.setLayoutManager(layoutManager);
                        MainActivity activity=(MainActivity)getActivity();
                        activity.toolbar.setTitle("爱心喇叭");
                        UserMessageAdapter  adapter = new UserMessageAdapter(userMessagesList,activity.TOKEN);
                        recyclerView.setAdapter(adapter);
                        //  closeProgressDialog();
//                        Toast.makeText(getContext(),"加载成功",Toast.LENGTH_SHORT).show();
                        //  show();
                    }
                });
            }
        });
    }
    ;
}
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626

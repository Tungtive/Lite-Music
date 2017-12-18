package com.DC.android;

import android.graphics.Bitmap;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.DC.android.db.Plan;
import com.DC.android.db.UserMessage;
import com.DC.android.util.HttpUtil;
import com.DC.android.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

=======
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.DC.android.util.HttpUtil;
import com.DC.android.util.UploadUtil;
import com.github.mikephil.charting.charts.LineChart;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
<<<<<<< HEAD
=======
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
import okhttp3.Response;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by XZJ on 2017/11/11.
 */

public class AddFragment extends android.support.v4.app.Fragment {
<<<<<<< HEAD

    WebView mWebView;
    WebSettings mWebSettings;

=======
    private GridView gridView;
    private GvAdapter adapter;
    private List<String> list;
    private List<String>  list_path;
    private EditText projectTitle;
    private EditText projectMoney;
    private EditText proejctDescription;
    private EditText projectDetail;
    ImagePicker imagePicker ;
    String path;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_add,container,false);
        MainActivity activity=(MainActivity)getActivity();
        activity.toolbar.setTitle("发布项目");
        gridView = (GridView) view.findViewById(R.id.grid_view);
        projectTitle=view.findViewById(R.id.add_title_edit);
        projectMoney=view.findViewById(R.id.add_targetMoney_edit);
        proejctDescription=view.findViewById(R.id.add_description_edit);
        projectDetail=view.findViewById(R.id.add_content_edit);
        Button button =view.findViewById(R.id.submit);
        initView();
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MainActivity activity=(MainActivity)getActivity();
                sendWordInfo(projectTitle.getText()+"",projectMoney.getText()+"",proejctDescription.getText()+"",projectDetail.getText()+"",activity.TOKEN,activity.user.getName());

             //   for( int i=0;i<fileList.size();i++) {
          //          String result = UploadUtil.uploadFile(fileList.get(i), RequestURL);
             //   }
           }
        });
        return view;
    }
   public void sendWordInfo(String projectTitle, String projectMoney, String projectDescription, String projectDeail, final String token, String initiatorName){
       String Url="http://139.199.162.139:8080/user/publish-project";
       HttpUtil.sendAddProjectOkHttpRequest(Url,token,projectTitle,initiatorName,projectDescription,projectMoney,projectDeail,new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
               if (getActivity() == null) return;  //getActivity()有可能为空 防止空指针异常
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();

                   }
               });
           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               String responseText = response.body().string();
                String ok="";
               String newProjectId="";
               if (getActivity() == null) return;  //getActivity()有可能为空 防止空指针异常
               try {
                   JSONObject jsonObject=new JSONObject(responseText);
                    ok=jsonObject.getString("ok");
                   newProjectId=jsonObject.getString("newProjectId");
               }catch (JSONException e){
                   e.printStackTrace();
               }
               final String finalOk = ok;
               final String finalNewProjectId=newProjectId;
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       Toast.makeText(getContext(),finalOk, Toast.LENGTH_SHORT).show();
                       sendRequestWithHttpURLConnection(getContext(),token,finalNewProjectId);
                   }
               });
           }
       });

   }
    public void sendRequestWithHttpURLConnection(final Context context, final String token, final String newProjectId){
        new Thread(new Runnable() {
            @Override
            public void run() {
              //  String RequestURL="http://139.199.162.139:8080/user/pic-upload-test";
        //        File file= new File(String.valueOf(list.get(0)));
                boolean boo= UploadUtil.uploadImg(list_path.get(0),token,newProjectId);
                if (boo){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity activity = (MainActivity) getActivity();
                            Toast.makeText(context, "上传成功，请等待审核", Toast.LENGTH_SHORT).show();
                            activity.replaceFragment(new HomeFragment());
                        }
                    });
                }
                }
        }).start();
    }
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        mWebView =(WebView)view.findViewById(R.id.webView2);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://118.89.20.206/regaliastar_toplist/");
        mWebView.setWebViewClient(new HelloWebViewClient ());
        final MainActivity activity = (MainActivity) getActivity();
        activity.toolbar.setTitle("歌单");
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
    private View.OnKeyListener backlistener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                if (i == KeyEvent.KEYCODE_BACK) {  //表示按返回键 时的操作
                    if ( mWebView.canGoBack()) {
                        mWebView.goBack();
                        return true;
                    } //后退
                    return false;    //已处理
                }
            }
            return false;
        }
    };

}
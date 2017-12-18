package com.DC.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.DC.android.db.User;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText email_edit;
    private EditText password_edit;
    private Button button_login;
    private TextView register_textView,forget_textView;
    String email = "";
    String pswd = "";
    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        setContentView(R.layout.activity_login);
        fullScreen(this);
        email_edit = (EditText) findViewById(R.id.email_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);
        button_login = (Button) findViewById(R.id.login);
        register_textView=(TextView)findViewById(R.id.register);
     //   forget_textView=(TextView)findViewById(R.id.forget);
        register_textView.setOnClickListener(this);
      //  forget_textView.setOnClickListener(this);
        button_login.setOnClickListener(this);
    }
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }




    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.login) {
            email = email_edit.getText().toString();
            pswd = password_edit.getText().toString();
            //   Toast.makeText(LoginActivity.this,email, Toast.LENGTH_SHORT).show();
            if ((email_edit.getText().toString().isEmpty()) || (password_edit.getText().toString().isEmpty()))
                Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
            else {
<<<<<<< HEAD
               //  sendRequestWithOkHttp();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
=======
             //  sendRequestWithOkHttp();
               Intent intent = new Intent(LoginActivity.this,MainActivity.class);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.register) {
            Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
//        if (v.getId() == R.id.forget) {
//            Toast.makeText(LoginActivity.this, "找回密码", Toast.LENGTH_SHORT).show();
//        }
    }

    public void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //发送请求
                    RequestBody requestBody = new FormBody.Builder()
                            .add("email", email).add("password", pswd)
                            .build();
                    OkHttpClient     okHttpClient = new OkHttpClient.Builder()
                            .cookieJar(new CookieJar() {
                                private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
                                @Override
                                public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                                    cookieStore.put(httpUrl, list);
                                }

                                @Override
                                public  List<Cookie> loadForRequest(HttpUrl httpUrl){
                                    List<Cookie> cookies = cookieStore.get(httpUrl);
                                    return cookies != null ? cookies : new ArrayList<Cookie>();
                                }
                            }).build();
                    Request request = new Request.Builder().url("http://118.89.20.206/login/cellphone?phone="+email+"&"+"password="+pswd).
                            build();

                    //返回结果
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    parseISONWithJSONObject(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseISONWithJSONObject(String jsonData){
        try{
        //    JSONArray jsonArray = new JSONArray(jsonData);
          //    jsonObject = jsonArray.getJSONObject();
            Log.i("LoginActivity",jsonData);
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONObject accountObject=jsonObject.getJSONObject("account");
            String  ok=accountObject.getString("id");

            if(ok.equals("1")||true){
          //       Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userId",ok);
                startActivity(intent);
                // Toast.makeText(LoginActivity.this, "找回密码", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        //        intent.putExtra(MainActivity.PROJECT_NAME,project.getProjectName());
          //      intent.putExtra(MainActivity.PROJECT_IMAGE_ID,project.getImg());
               // startActivity(intent);

            }else if(ok.equals("false")) {
                String reason = jsonObject.getString("reason");
                Toast.makeText(LoginActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
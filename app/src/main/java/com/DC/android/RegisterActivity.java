package com.DC.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText email_edit;
    private EditText password_edit;
    private EditText password2_edit;
    private EditText name_edit;
    private EditText region_edit;
    private Button signup;
    String email = "";
    String pswd = "";
    String pswd2 = "";
    String gender = "";
    String name = "";
    String region = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email_edit = (EditText) findViewById(R.id.email_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);
        password2_edit = (EditText) findViewById(R.id.password2_edit);
        name_edit = (EditText) findViewById(R.id.name_edit);
        region_edit = (EditText) findViewById(R.id.region_edit);
        signup = (Button) findViewById(R.id.signup);
        RadioGroup group = (RadioGroup)this.findViewById(R.id.radioGroup);

        signup.setOnClickListener(this);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.female == checkedId){
                    gender = "female";
                }
                else if(R.id.male == checkedId){
                    gender = "male";
                }
            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.signup) {
            email = email_edit.getText().toString();
            pswd = password_edit.getText().toString();
            pswd2 = password2_edit.getText().toString();
            name= name_edit.getText().toString();
            region = region_edit.getText().toString();
            String regex="\\w+@\\w+(\\.\\w+)+";

            //检查邮箱地址，两次密码一致，信息是否完整
            if(name==null || region == null || pswd == null || pswd2 == null || email == null){
                Toast.makeText(RegisterActivity.this, "信息不能为空！", Toast.LENGTH_SHORT).show();
            }else if(!email.matches(regex)){
                Toast.makeText(RegisterActivity.this, "邮箱格式不正确！", Toast.LENGTH_SHORT).show();
            }else if(!pswd2.equals(pswd)){
                Toast.makeText(RegisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            }else{ sendRequestWithOkHttp();}
        }
    }

    public void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //发送请求
                    RequestBody requestBody = new FormBody.Builder()
                            .add("email", email).add("password", pswd).add("gender", gender).add("name", name).add("region", region)
                            .build();
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://139.199.162.139:8080/user/register").post(requestBody).build();

                    //接收结果
                    Response response = client.newCall(request).execute();
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
            JSONObject jsonObject = new JSONObject(jsonData);
            String ok = jsonObject.getString("ok");

            if("true".equals(ok)){
                //Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                //intent.putExtra("email",email);
                startActivity(intent);
            }else if(ok.equals("false")) {
                String reason = jsonObject.getString("reason");
                Toast.makeText(RegisterActivity.this, reason, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

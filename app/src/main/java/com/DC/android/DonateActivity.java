package com.DC.android;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.DC.android.db.Project;
import com.DC.android.db.ProjectMessage;
import com.DC.android.util.HttpUtil;
import com.DC.android.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DonateActivity extends AppCompatActivity {
     private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        setContentView(R.layout.activity_donate);
        Intent intent=getIntent();
        token =(String) intent.getStringExtra("token");
        final Project project=(Project) intent.getSerializableExtra("project");
        Toolbar toolbar=(Toolbar) findViewById(R.id.toobar);
        final EditText inputMoney=(EditText) findViewById(R.id.input_money);
        final EditText message=(EditText)findViewById(R.id.message);
        final TextView totalMoney=(TextView) findViewById(R.id.totalMoney);
        Button  pay=(Button) findViewById(R.id.pay);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Editview 输入监听
        inputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string=inputMoney.getText()+"元";
                totalMoney.setText(string);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //pay 按钮
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("".equals(inputMoney.getText()+""))       Toast.makeText(DonateActivity.this,"请输入捐助金额",Toast.LENGTH_SHORT).show();
               else{
                String projectUrl="http://139.199.162.139:8080/user/donate";
                //   OkHttpClient moKHttpClient =new OkHttpClient();
                String message_st="";
                if ((message.getText()+"").equals(""))  message_st="Ta什么也没留下";
                else  message_st=message.getText()+"";
                HttpUtil.sendDonateOkHttpRequest(projectUrl,token,project.getPid(),message_st,inputMoney.getText().toString(),new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //  closeProgressDialog();
                                Toast.makeText(DonateActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                                //  show();
                            }
                        });
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseText =response.body().string();
                        String ok="",reason="";
                        Log.i("DonateActivity",responseText);
                        Log.i("DonateActivity",""+project.getId());
                        Log.i("DonateActivity",inputMoney.getText().toString());
<<<<<<< HEAD
                   //     boolean result=  Utility.handleProjectResponce(responseText);
=======
                        boolean result=  Utility.handleProjectResponce(responseText);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                        try {
                            JSONObject jsonObject=new JSONObject(responseText);
                             ok=jsonObject.getString("ok");
                            if (ok.equals("false")) reason=jsonObject.getString("reason");

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        final String finalOk = ok;
                        final String finalReason = reason;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //  closeProgressDialog();
                                if (finalOk.equals("true")){
                                Toast.makeText(DonateActivity.this,"捐感谢您的支持！",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DonateActivity.this, ProjectMessageActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("project",project);
                                intent.putExtras(bundle);
                                intent.putExtra("token",token);
                                DonateActivity.this.startActivity(intent);}
                                else
                                Toast.makeText(DonateActivity.this,finalReason, Toast.LENGTH_SHORT).show();

                                //  show();
                            }
                        });
                    }
                });

            }
        }});

    }
}

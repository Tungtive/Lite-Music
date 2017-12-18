package com.DC.android;

import android.content.Intent;
import android.net.ParseException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.DC.android.db.Plan;
import com.DC.android.db.Project;
import com.DC.android.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Add_PlanActivity extends AppCompatActivity {

    private EditText add_plan_title;
    private EditText add_plan_value;
    private EditText add_plan_date;
    private EditText add_plan_deadLine;
    private Button   submit_plan;
    private Plan plan=new Plan();
    private String token;
    private String fragmentName="plan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        setContentView(R.layout.activity_add__plan);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toobar);
        Intent intent=getIntent();
        token =(String) intent.getStringExtra("token");
        add_plan_date = (EditText) findViewById(R.id.add_plan_date);
        add_plan_title = (EditText) findViewById(R.id.add_plan_title);
        add_plan_value=(EditText) findViewById(R.id.add_plan_value);
        add_plan_deadLine=(EditText) findViewById(R.id.add_end_date) ;
        submit_plan=(Button) findViewById(R.id.submit);

        submit_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((add_plan_title.getText()+"")!=null) {
                    addPlan(add_plan_title.getText()+"");
                };
            }
        });

        //获取当期日期
        SimpleDateFormat formatter=new SimpleDateFormat ("yyyy/MM/dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间  
        Date endDate=addDate(curDate,21);
        String curDate_str = formatter.format(curDate);
        add_plan_date.setHint(curDate_str);
        add_plan_deadLine.setHint(formatter.format(endDate));
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public static Date addDate(Date date,long day) throws ParseException {
     long time = date.getTime(); // 得到指定日期的毫秒数
     day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
     time+=day; // 相加得到新的毫秒数
     return new Date(time); // 将毫秒数转换成日期
    }
private void  addPlan(String planName) {
    String Url = "http://139.199.162.139:8080/user/add-plan";
    HttpUtil.sendPlanAddOkHttpRequest(Url, token,planName ,new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //  closeProgressDialog();
                    Toast.makeText(Add_PlanActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                    //  show();
                }
            });
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String responseText = response.body().string();
            Log.i("Add_PlanActivity", responseText);
            String ok="",reason="";
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject = new JSONObject(responseText);
                ok = jsonObject.getString("ok");
                ;if (ok.equals("false"))  reason=jsonObject.getString("reason");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            final String finalOk = ok;
            final String finalReason = reason;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (finalOk.equals("true")) {
                        Toast.makeText(Add_PlanActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Add_PlanActivity.this, MainActivity.class);
                        //      intent.putExtra("EMAIL",email);
                        intent.putExtra("token",token);
                        intent.putExtra("fragmentName",fragmentName);
                        startActivity(intent);
                    }

                    else    Toast.makeText(Add_PlanActivity.this, finalReason, Toast.LENGTH_SHORT).show();

                }
            });
        }
    });
}
}

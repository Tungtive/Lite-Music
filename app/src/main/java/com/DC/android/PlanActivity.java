package com.DC.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.DC.android.calenda.EventDecorator;
import com.DC.android.db.Plan;
import com.DC.android.util.HttpUtil;
import com.DC.android.util.Utility;
import com.bumptech.glide.Glide;
import com.coder.circlebar.CircleBar;
import com.daimajia.numberprogressbar.NumberProgressBar;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;


import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PlanActivity extends AppCompatActivity {
    private CircleBar progress;
    private CircleImageView  sign;
    private MaterialCalendarView myCalendar;
    private Button deletePlan ;
    private String token;
    private NumberProgressBar numberProgressBar;
    private String fragmentName="plan";
    boolean isrung = false;
    boolean isSign=false;
    float image_alpha=0f;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        SimpleDateFormat formatter=new SimpleDateFormat ("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间  
        String curDate_str = formatter.format(curDate);
        setContentView(R.layout.activity_plan);
        Intent intent=getIntent();
        final Plan plan= (Plan) intent.getSerializableExtra("plan");
        token=(String) intent.getStringExtra("token");

        if (curDate_str.equals(plan.getLastClock())) isSign=true;
        Toolbar toolbar=(Toolbar) findViewById(R.id.toobar);
        toolbar.setTitle(plan.getPlanName()+"  （"+curDate_str+"）");
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanActivity.this, MainActivity.class);
                //      intent.putExtra("EMAIL",email);
                intent.putExtra("token",token);
                intent.putExtra("fragmentName",fragmentName);
                startActivity(intent);
            }
        });

        deletePlan=(Button)findViewById(R.id.deletePlan);
        progress = (CircleBar) findViewById(R.id.progress);
        sign=(CircleImageView)findViewById(R.id.sign) ;
        progress.setColor(Color.parseColor("#3dd086") );

        //进度条
         numberProgressBar=(NumberProgressBar) findViewById(R.id.number_progress_bar);
         numberProgressBar.setMax(21);
         numberProgressBar.setProgress(Integer.parseInt(plan.getFinishedTimes()));
        //日历
        myCalendar=(MaterialCalendarView) findViewById(R.id.calendarView);
        myCalendar.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        Calendar instance = Calendar.getInstance();
        myCalendar.setSelectedDate(instance.getTime());
        Calendar instance1 = Calendar.getInstance();
        instance1.set(instance1.get(Calendar.YEAR), Calendar.JANUARY, 1);
        Calendar instance2 = Calendar.getInstance();
        instance2.set(instance2.get(Calendar.YEAR), Calendar.DECEMBER, 31);
        myCalendar.state().edit()
                .setMinimumDate(instance1.getTime())
                .setMaximumDate(instance2.getTime())
                .commit();
        Collection<CalendarDay> dates=new ArrayList<>();

//        dates.add(new CalendarDay(2017,10,28));
//        dates.add(new CalendarDay(2017,10,29));
//        dates.add(new CalendarDay(2017,10,30));
//        dates.add(new CalendarDay(2017,11,01));
//        dates.add(new CalendarDay(2017,11,02));
        if (plan.getLastClock()!=null) {
            Date startDate = java.sql.Date.valueOf(plan.getStartDate());
            Date lastDate = java.sql.Date.valueOf(plan.getLastClock());
            dates = getBetweenDates(startDate, lastDate);
            if (dates.size() == 0) dates.add(new CalendarDay(startDate));
            myCalendar.addDecorator(new EventDecorator(R.color.colorAccent, dates, this));
        }
        if (!isSign) sign.setImageResource(R.drawable.ic_fault);
        else {
            sign.setImageResource(R.drawable.ic_true);
            progress.update(30, 0);
        }

        deletePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       deletePlan(plan.getPlamId());
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSign == false) {
                    isrung = true;
                    sign.setImageResource(R.drawable.ic_true);
                    sign.setAlpha(0f);
                    progress.update(30, 1200);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (isrung) {
                                try {
                                    Thread.sleep(250);
// 更新Alpha值
                                    updateAlpha();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                    mHandler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg);
                            sign.setAlpha(image_alpha);
                            // 刷新视图
                            sign.invalidate();
                        }
                    };
                    isSign=true;
                    String projectUrl="http://139.199.162.139:8080/user/clock";
                    //   OkHttpClient moKHttpClient =new OkHttpClient();
                    HttpUtil.sendPlanSignOkHttpRequest(projectUrl,token,plan.getPlamId(),new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //  closeProgressDialog();
                                    Toast.makeText(PlanActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                                    //  show();
                                }
                            });
                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseText =response.body().string();
                            Log.i("planActivity",responseText);
                            String ok="";
                            String reason="";
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject = new JSONObject(responseText);
                                ok = jsonObject.getString("ok");
                                reason=jsonObject.getString("reason");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            final String finalOk = ok;
                            final String finalReason = reason;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //  closeProgressDialog();
                                    if("true".equals(finalOk))  Toast.makeText(PlanActivity.this, finalReason,Toast.LENGTH_SHORT).show();



                                    //  show();
                                }
                            });
                        }
                    });

                    Collection<CalendarDay> dates=new ArrayList<>();
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间 
                    dates.add(new CalendarDay(curDate));
                    myCalendar.addDecorator(new EventDecorator(R.color.colorAccent,dates,PlanActivity.this));
                    numberProgressBar.setProgress(Integer.parseInt(plan.getFinishedTimes())+1);
                }
                else    Toast.makeText(PlanActivity.this,"今天已签到！", Toast.LENGTH_SHORT).show();
            }

        });



    }

    private void deletePlan(int planId){
        String Url = "http://139.199.162.139:8080/user/del-plan";
        HttpUtil.sendPlanDeleteOkHttpRequest(Url, token,planId ,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  closeProgressDialog();
                        Toast.makeText(PlanActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                        //  show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Log.i("PlanActivity", responseText);
                String ok="";
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject = new JSONObject(responseText);
                     ok = jsonObject.getString("ok");
                    ;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final String finalOk = ok;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       if ("true".equals(finalOk)) {
                           Toast.makeText(PlanActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(PlanActivity.this, MainActivity.class);
                           //      intent.putExtra("EMAIL",email);
                           intent.putExtra("token", token);
                           intent.putExtra("fragmentName", fragmentName);
                           startActivity(intent);
                       }

                    }
                });
            }
        });
    }

    public void updateAlpha() {
        if (image_alpha +0.1f <= 1f) {
            image_alpha = image_alpha+0.1f;
        } else {
            image_alpha = 1f;
            isrung = false;
        }
// 发送需要更新imageview视图的消息-->这里是发给主线程
        mHandler.sendMessage(mHandler.obtainMessage());
    }

//    @Override
//    public  boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case android.R.id.home:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //获得两个日期之间得所有日期
    private ArrayList<CalendarDay> getBetweenDates(Date start, Date end) {
        ArrayList<CalendarDay> result = new ArrayList<CalendarDay>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        result.add(new CalendarDay(start));
        result.add(new CalendarDay(end));
        while (tempStart.before(tempEnd)) {
            result.add(new CalendarDay(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return  result;
    }

}

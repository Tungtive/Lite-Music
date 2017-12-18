package com.DC.android;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.prefs.*;
import com.DC.android.PersonalScrollView.onTurnListener;
import com.DC.android.RotateAnimation.InterpolatedTimeListener;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.SharedPreferencesCompat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.DC.android.db.Plan;
import com.DC.android.db.Project;
import com.DC.android.util.HttpUtil;
import com.DC.android.util.Utility;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ResourceBundle;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.preference.PreferenceManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.DC.android.PersonalScrollView.onTurnListener;
import com.DC.android.RotateAnimation.InterpolatedTimeListener;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import static android.R.string.ok;
import static com.DC.android.R.id.image_header;


/**
 * Created by XZJ on 2017/10/17.
 */

public class PlanFragment extends android.support.v4.app.Fragment  {
    private ImageView bingPicImg;

   private int drawable_id[] = { R.drawable.nav_head,
           R.drawable.ic_add_plan };
    private TableLayout tl_main;
    private com.DC.android.PersonalScrollView personalScrollView;
    private int current_id;
    private View line_up;
    private ImageView iv_personal_bg;
    private ImageView image_header;
    private TextView  text_header;
    private TextView  text_header_date;
    private List<Plan> planList =null;
    private PlanAdapter adapter;//爱心项目显示适配器
    private boolean boo=true;//翻转
    private int plan_position=0;//翻转
    private RecyclerView recyclerView;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        LayoutInflater inflator = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.fragment_plan,null);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_plan,container,false);

        personalScrollView = (PersonalScrollView) view.findViewById(R.id.personalScrollView);
      //  tl_main = (TableLayout) view.findViewById(R.id.tl_main);
        line_up = (View) view.findViewById(R.id.line_up);
        iv_personal_bg = (ImageView) view.findViewById(R.id.iv_personal_bg);
        image_header = (ImageView) view.findViewById(R.id.image_header);
        text_header=(TextView) view.findViewById(R.id.text_header);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_plan);
        text_header_date=(TextView) view.findViewById(R.id.text_header_date);
        text_header.bringToFront();//置于顶层
        final MainActivity activity = (MainActivity) getActivity();
        activity.toolbar.setTitle("我的计划");
        initPlans(activity.TOKEN);
        Glide.with(getContext()).load("http://139.199.162.139:8080"+activity.user.getHead()).into(image_header);
        personalScrollView.setTurnListener(new PersonalScrollView.onTurnListener(){
            @Override
            public void onTurn() {
                RotateAnimation animation = new RotateAnimation();
               // animation.setFillAfter(true);
               animation.setInterpolatedTimeListener(new RotateAnimation.InterpolatedTimeListener(){
                   @Override
                    public void interpolatedTime(float interpolatedTime) {
                        // 监听到翻转进度过半时，更新图片内容，
                       if (interpolatedTime > 0.5f) {
                           Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                   drawable_id[current_id]);

                           if (drawable_id[current_id]== R.drawable.nav_head)    Glide.with(getContext()).load("http://139.199.162.139:8080"+activity.user.getHead()).into(image_header);
                             else image_header.setImageBitmap(bitmap);

                       }
                    }
                });

                image_header.startAnimation(animation);
                current_id = current_id < drawable_id.length - 1 ? ++current_id : 0;
            }

        });
        image_header.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (drawable_id[current_id]==R.drawable.ic_add_plan) {
                    Intent intent = new Intent(getActivity(), Add_PlanActivity.class);
       //             intent.putExtra("plan_num",planList.size()+1);
                    MainActivity activity=(MainActivity)getActivity();
                    intent.putExtra("token",activity.TOKEN);
                    startActivity(intent);
                }
            }
        });
        personalScrollView.setImageView(iv_personal_bg);// 背景
        personalScrollView.setLine_up(line_up);
        //DataSupport.deleteAll(Plan.class);



      //  text_header_date.setText("From: "+planList.get(plan_position+1).getStartDate());
      //  if (planList.size()==0)   initPlans();



        // showTable();
        return view;
    }

    private void initPlans(final String token){
       // planList.clear();
//        for (int i=0;i<30;i++){
//            Random random =new Random();
//            int index = random.nextInt(plans.length);
//            planList.add(plans[index]);
//        }
        String planUrl="http://139.199.162.139:8080/user/get-plans";
        final MainActivity activity=(MainActivity)getActivity();
<<<<<<< HEAD
        HttpUtil.sendOkHttpRequest(planUrl,new Callback() {
=======
        HttpUtil.sendOkHttpRequest(planUrl, activity.TOKEN,new Callback() {
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
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
                Log.i("MainActivity",responseText);
                boolean result=  Utility.handlePlanResponce(responseText);
                if  (getActivity() == null) return;  //getActivity()有可能为空 防止空指针异常
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  closeProgressDialog();
                 //       Toast.makeText(getContext(),"加载成功",Toast.LENGTH_SHORT).show();
                        planList= DataSupport.findAll(Plan.class);
                        if  (planList.size()>0) {
                            text_header.setText(planList.get(plan_position).getFinishedTimes());

                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            // layoutManager.setSmoothScrollbarEnabled(true);
                            recyclerView.setLayoutManager(layoutManager);
                            PlanAdapter adapter = new PlanAdapter(planList,token);
                            recyclerView.setAdapter(adapter);
                        }
                        //  show();
                    }
                });
            }
        });
    }

    private void loadBingPic(){
        String requestBingPic="http://guolin.tech/api/bing_pic";
       OkHttpClient moKHttpClient =new OkHttpClient();
        Request request = new Request.Builder().url(requestBingPic).build();
        Call call=moKHttpClient.newCall(request);
        call.enqueue(
                new Callback() {
            @Override
            public void onFailure(Call call,IOException e){
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Glide.with(getContext()).load(R.drawable.ic_background).into(bingPicImg);
                final String bingPic = response.body().string();

                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                if (getActivity()!=null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(getContext()).load(bingPic).into(bingPicImg);
                        }

                    });
            }
        });
}


}
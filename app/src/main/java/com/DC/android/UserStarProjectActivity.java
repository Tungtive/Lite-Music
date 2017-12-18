package com.DC.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
<<<<<<< HEAD
import android.support.v7.widget.LinearLayoutManager;
=======
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.DC.android.db.Project;
<<<<<<< HEAD
import com.DC.android.db.RecommendSong;
import com.DC.android.util.HttpUtil;
import com.DC.android.util.Utility;
=======
import com.DC.android.util.HttpUtil;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
<<<<<<< HEAD
import org.litepal.crud.DataSupport;
=======
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserStarProjectActivity extends AppCompatActivity {
<<<<<<< HEAD
    private List<Project> ProjectList = new ArrayList<Project>();
    private List<Project> myProjectList = new ArrayList<Project>();
    private String token;
    // private   RecyclerView follow_project;
    private RecyclerView my_project;
    private ProjectAdapter adapter;

=======
    private List<Project> followProjectList=new ArrayList<Project>();
    private List<Project> myProjectList=new ArrayList<Project>();
    private String token;
    // private   RecyclerView follow_project;
    private  RecyclerView my_project;
    private ProjectAdapter adapter;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        setContentView(R.layout.activity_user_star);
<<<<<<< HEAD
        Intent intent = getIntent();
        token = (String) intent.getStringExtra("token");
        // follow_project = (RecyclerView) findViewById(R.id.follow_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
=======
        Intent intent=getIntent();
        token =(String) intent.getStringExtra("token");
        // follow_project = (RecyclerView) findViewById(R.id.follow_project);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        my_project = (RecyclerView) findViewById(R.id.my_project);
        initProjects(this);

    }
<<<<<<< HEAD

    private void initProjects(final Context context) {
        String Url = "http://118.89.20.206/recommend/songs";
        HttpUtil.sendOkHttpRequest(Url, new Callback() {
=======
    private void initProjects(final Context context){
        String projectUrl="http://139.199.162.139:8080/user/get-related-projects";
        HttpUtil.sendOkHttpRequest(projectUrl,token,new Callback() {
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
<<<<<<< HEAD

                        Toast.makeText(UserStarProjectActivity.this, "加载失败", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Log.i("recommendsongs", responseText);
                boolean result = Utility.handleRecommendSongResponce(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        projectList= DataSupport.findAll(RecommendSong.class);
//                        //    if (projectList.size()==0) initProjects();//初始化爱心项目数组
//                        if  (projectList.size()>0)
//                        {
//                            LinearLayoutManager layoutManager = new  LinearLayoutManager(getContext());
////                            layoutManager.setStackFromEnd(true);
////                            layoutManager.setReverseLayout(true);
//                            recyclerView.setLayoutManager(layoutManager);
//                            MainActivity activity=(MainActivity)getActivity();
//                            activity.toolbar.setTitle("主页");
//                            adapter = new ProjectAdapter(projectList);
//                            recyclerView.setAdapter(adapter);
//                        }
                        //  show();
=======
                        //  closeProgressDialog();
                        Toast.makeText(UserStarProjectActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                        //  show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText =response.body().string();
                Log.i("UserStarProject",responseText);
                try {
                    JSONObject jsonObject=new JSONObject(responseText);
                    JSONArray userProjects=jsonObject.getJSONArray("userStar");
                    for (int i=0;i< userProjects.length();i++)
                    {
                        JSONObject projectObject = userProjects.getJSONObject(i);
                        Project project=new Project();
                        project.setProjectName(projectObject.getString("projectName"));
                        project.setCurrentMoney(projectObject.getDouble("currentMoney"));
                        project.setTargetMoney(projectObject.getDouble("targetMoney"));
                        project.setDescription(projectObject.getString("description"));
                        project.setDetail(projectObject.getString("detail"));
                        project.setImg(projectObject.getString("img"));
                        project.setInitiatorName(projectObject.getString("initiatorName"));
                        project.setPeopleNum(projectObject.getInt("peopleNumber"));
                        project.setStartTime(projectObject.getString("startTime"));
                        project.setImgListStr(projectObject.getString("imgListStr"));
                        project.setPid(projectObject.getInt("id"));
                        myProjectList.add(project);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (myProjectList.size()>0) {
                            GridLayoutManager myLayoutManager = new GridLayoutManager(UserStarProjectActivity.this,1);
                            my_project.setLayoutManager(myLayoutManager);
                            adapter = new ProjectAdapter(myProjectList,token);
                            my_project.setAdapter(adapter);
                        }
                        Toast.makeText(UserStarProjectActivity.this,"加载成功",Toast.LENGTH_SHORT).show();
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                    }
                });
            }
        });
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626

package com.DC.android;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.DC.android.db.Project;
import com.DC.android.db.ProjectMessage;
<<<<<<< HEAD
import com.DC.android.db.RecommendSong;
=======
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
import com.DC.android.util.HttpUtil;
import com.DC.android.util.Utility;
import com.bumptech.glide.Glide;

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

import static java.sql.Types.NULL;
import static org.litepal.LitePalApplication.getContext;

public class ProjectActivity extends AppCompatActivity {
    public static final String PROJECT_NAME ="project_name";
    public static final String PROJECT_IMAGE_ID="project_image_id";
    private ProjectPhotoSetAdaper adapter;
    private List<String> photoList = new ArrayList<>();
    private boolean follow=false;
    private String token;
    private   TextView projectCurrentMoney;
    private SwipeRefreshLayout swipeRefresh;//下拉刷新控件
    private ImageView ImageWatch ;
<<<<<<< HEAD
    private RecommendSong song;
    private String songLyric;
=======
    private  Project project;
    private TextView   projectHelperNumber;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        setContentView(R.layout.activity_project);

        Intent intent=getIntent();
<<<<<<< HEAD
        song=(RecommendSong) intent.getSerializableExtra("song");
=======
         project=(Project) intent.getSerializableExtra("project");
        token =(String) intent.getStringExtra("token");
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
        Toolbar toolbar =(Toolbar)findViewById(R.id.toobar);
        CollapsingToolbarLayout collapsingToolbar =(CollapsingToolbarLayout) findViewById(R.id.collapsing_toobar);
        ImageView projectImageView=(ImageView)findViewById(R.id.project_image_view);
        TextView  projectContentText=(TextView) findViewById(R.id.project_content_text);
<<<<<<< HEAD
        TextView  albumName=(TextView) findViewById(R.id.album_name);
        albumName.setText(song.getAlbumName());
        TextView  albumCompany=(TextView) findViewById(R.id.album_company);
        albumCompany.setText(song.getAlbumCompany());
        TextView  albumType=(TextView) findViewById(R.id.album_type);
        albumType.setText(song.getAlbumType());
        TextView  albumPublishTime=(TextView) findViewById(R.id.album_publish_time);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String publishDate = format.format(Long.parseLong(song.getAlbumPublishTime()));
        albumPublishTime.setText(publishDate);
=======
         projectCurrentMoney=(TextView) findViewById(R.id.project_current_money);
        TextView  projectDate=(TextView) findViewById(R.id.project_date);
        TextView  projectTargetMoney=(TextView) findViewById(R.id.project_target_money);
        projectHelperNumber=(TextView) findViewById(R.id.project_helper_num);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
        TextView  projectDescription=(TextView) findViewById(R.id.project_description);
        projectDescription.setText(song.getReason());
        getLyric(song.getId());
        FloatingActionButton  projectMessage=(FloatingActionButton) findViewById(R.id.projectMessage);
        projectMessage.setImageResource(R.drawable.ic_money_menu);
        ImageWatch=(ImageView) findViewById(R.id.Image_watch);
        Button  buttonDonate=(Button) findViewById(R.id.donate);
        Log.d("id",song.getId()+"");

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
         actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  finish();
                Intent intent = new Intent(ProjectActivity.this, MainActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
<<<<<<< HEAD
        collapsingToolbar.setTitle(song.getName());
        Glide.with(this).load(song.getAlbumPicUrl()).into(projectImageView);
        String projectContent=generateProjectContent(song.getName());
        String artistName="";
        for (int i=0;i<song.getAritistName().size();i++)
        {
            artistName=artistName+song.getAritistName().get(i)+"  ";
        }
        projectContentText.setText(artistName);

        //详情图片

      //  starProjects("http://139.199.162.139:8080/user/if-has-stared",project.getPid());
=======
        collapsingToolbar.setTitle(project.getProjectName());
        Glide.with(this).load("http://139.199.162.139:8080"+project.getImg()).into(projectImageView);
        String projectContent=generateProjectContent(project.getProjectName());
        projectContentText.setText(project.getDetail());
        projectCurrentMoney.setText(Double.toString(project.getCurrentMoney()));
        projectTargetMoney.setText(Double.toString(project.getTargetMoney()));
        projectHelperNumber.setText(Double.toString(project.getPeopleNum()));
        projectDescription.setText(project.getDescription());
        projectDate.setText(project.getStartTime());
        //详情图片
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_photo);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ProjectPhotoSetAdaper adapter = new ProjectPhotoSetAdaper(photoList);
        recyclerView.setAdapter(adapter);
        starProjects("http://139.199.162.139:8080/user/if-has-stared",project.getPid());
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
        //关注
        //实例化下拉刷新控件
        swipeRefresh =(SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshProjects();

            }
        });
        ImageWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (follow==false) {
                    ImageWatch.setImageResource(R.drawable.ic_star_1);
                    ImageWatch.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
<<<<<<< HEAD
              //      starProjects("http://139.199.162.139:8080/user/star-project",project.getPid());
=======
                    starProjects("http://139.199.162.139:8080/user/star-project",project.getPid());
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                    follow=true;
                }
                else {
                    ImageWatch.setImageResource(R.drawable.ic_star);
                    ImageWatch.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
<<<<<<< HEAD
                //    starProjects("http://139.199.162.139:8080/user/cancel-start-project",project.getPid());
=======
                    starProjects("http://139.199.162.139:8080/user/cancel-start-project",project.getPid());
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                    follow=false;
                }
            }
        });

        projectMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, ProjectMessageActivity.class);
                Bundle bundle = new Bundle();
<<<<<<< HEAD
                bundle.putSerializable("song",song);
                intent.putExtras(bundle);
                startActivity(intent);
=======
                bundle.putSerializable("project",project);
                intent.putExtras(bundle);
                intent.putExtra("token",token);
                ProjectActivity.this.startActivity(intent);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
            }
        });

        buttonDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, DonateActivity.class);
                // intent.putExtra("EMAIL",email);
                 intent.putExtra("token",token);
                Bundle bundle = new Bundle();
                bundle.putSerializable("song",song);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }


    private  void refreshProjects(){
<<<<<<< HEAD
        //projectCurrentMoney.setText(Double.toString(project.getCurrentMoney()));
     //   adapter.notifyDataSetChanged();// 通知数据发生了变化
       // initProject();
        swipeRefresh.setRefreshing(false);//刷新事件结束并隐藏刷新进度条
    }

    private  void  getLyric(String songId){
        String Url="http://118.89.20.206/lyric?id="+songId;
        HttpUtil.sendOkHttpRequest(Url,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ProjectActivity.this,"加载歌词失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText =response.body().string();
                Log.i("lyric",responseText);
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject = new JSONObject(responseText);
                    JSONObject lrc = jsonObject.getJSONObject("lrc");
                    songLyric=lrc.getString("lyric");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       TextView  songLyricText=(TextView) findViewById(R.id.song_lyric);
                       songLyricText.setText(songLyric);
                    }
                });
            }
        });

    }
=======
        projectCurrentMoney.setText(Double.toString(project.getCurrentMoney()));
     //   adapter.notifyDataSetChanged();// 通知数据发生了变化
        initProject();
        swipeRefresh.setRefreshing(false);//刷新事件结束并隐藏刷新进度条
    }
     private void initProject(){
         String projectUrl="http://139.199.162.139:8080/user/get-project";
         HttpUtil.sendProjectOkHttpRequest(projectUrl,token,project.getPid(),new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         //  closeProgressDialog();
                         Toast.makeText(ProjectActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
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
                         JSONObject projectObject = jsonObject.getJSONObject("project");
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


                 }catch (JSONException e){
                     e.printStackTrace();
                 }
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         projectCurrentMoney.setText(Double.toString(project.getCurrentMoney()));
                         projectHelperNumber.setText(Double.toString(project.getPeopleNum()));
                         Toast.makeText(ProjectActivity.this,"已刷新",Toast.LENGTH_SHORT).show();
                     }
                 });
             }
         });
     }

>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
    private String generateProjectContent(String projectName){
        StringBuilder projectContent =new StringBuilder();
        for (int i=0;i<500;i++)
        {
            projectContent.append(projectName);
        }
        return projectContent.toString();
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void starProjects(final String Url,int projectId){

        HttpUtil.sendProjectOkHttpRequest(Url, token,projectId,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            runOnUiThread(new Runnable() {
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
                final String[] ok = {"false"};
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject = new JSONObject(responseText);
                     ok[0] = jsonObject.getString("ok");;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (Url.equals("http://139.199.162.139:8080/user/if-has-stared"))
                       if (ok[0].equals("true"))  {
                           ImageWatch.setImageResource(R.drawable.ic_star_1);
                           ImageWatch.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                           follow=true;
                       }
                       else {
                           ImageWatch.setImageResource(R.drawable.ic_star);
                           ImageWatch.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                           follow=false;
                       }
                        if (Url.equals("http://139.199.162.139:8080/user/star-project"))   Toast.makeText(ProjectActivity.this,"关注成功", Toast.LENGTH_SHORT).show();
                        if (Url.equals("http://139.199.162.139:8080/user/cancel-star-project"))   Toast.makeText(ProjectActivity.this,"取消关注", Toast.LENGTH_SHORT).show();
                        //  show();
                    }
                });
            }
        });
    }
}

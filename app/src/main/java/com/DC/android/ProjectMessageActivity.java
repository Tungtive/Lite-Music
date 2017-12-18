package com.DC.android;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

<<<<<<< HEAD
import com.DC.android.db.Hotcomment;
import com.DC.android.db.News;
import com.DC.android.db.Project;
import com.DC.android.db.ProjectMessage;
import com.DC.android.db.RecommendSong;
=======
import com.DC.android.db.News;
import com.DC.android.db.Project;
import com.DC.android.db.ProjectMessage;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
import com.DC.android.db.UserMessage;
import com.DC.android.util.HttpUtil;
import com.DC.android.util.Utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProjectMessageActivity extends AppCompatActivity {
    private String token;
<<<<<<< HEAD
    private RecommendSong  song;
    private   RecyclerView recyclerView;
    private List<Hotcomment> hotcommentList= new ArrayList<Hotcomment>();
=======
    private Project project;
    private   RecyclerView recyclerView;
    private List<ProjectMessage> projectMessagesList= new ArrayList<ProjectMessage>();
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        setContentView(R.layout.activity_project_message);
        Intent intent=getIntent();
<<<<<<< HEAD
        song=(RecommendSong) intent.getSerializableExtra("song");
=======
        token =(String) intent.getStringExtra("token");
        project=(Project) intent.getSerializableExtra("project");
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
        Toolbar toolbar=(Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  finish();
                 Intent intent = new Intent(ProjectMessageActivity.this, ProjectActivity.class);
<<<<<<< HEAD
                 Bundle bundle = new Bundle();
                bundle.putSerializable("song",song);
=======
                intent.putExtra("token",token);
                 Bundle bundle = new Bundle();
                bundle.putSerializable("project",project);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
<<<<<<< HEAD
        initMessage(song.getId());
         recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }
    private void initMessage(String songId){

        String Url="http://118.89.20.206/comment/music?id="+songId+"&limit=20";

        HttpUtil.sendOkHttpRequest(Url,new Callback() {
=======
        initMessage(project.getPid());
         recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }
    private void initMessage(int ProjectId){

        String Url="http://139.199.162.139:8080/user/get-all-donation";

        HttpUtil.sendProjectOkHttpRequest(Url,token,ProjectId,new Callback() {
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
            @Override
            public void onFailure(Call call, IOException e) {
              runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  closeProgressDialog();
                        Toast.makeText(ProjectMessageActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                        //  show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText =response.body().string();
                JSONObject jsonObject=new JSONObject();
<<<<<<< HEAD
                Log.i("SongCommentActivity",responseText);
                Log.i("id",song.getId()+"");
                try {
                    jsonObject = new JSONObject(responseText);
                    JSONArray allSongComment = jsonObject.getJSONArray("hotComments");;
                    for (int i=0;i<allSongComment.length();i++)
                    {
                        JSONObject hotCommentObject = allSongComment.getJSONObject(i);
                        Hotcomment hotcomment=new Hotcomment();
                        hotcomment.setTime(hotCommentObject.getString("time"));
                        hotcomment.setContent(hotCommentObject.getString("content"));
                        hotcomment.setLikedCount(hotCommentObject.getLong("likedCount"));
                        JSONObject hotCommentUser=hotCommentObject.getJSONObject("user");
                        hotcomment.setUserAvaturUrl(hotCommentUser.getString("avatarUrl"));
                        hotcomment.setUserNickName(hotCommentUser.getString("nickname"));
                        hotcommentList.add(hotcomment);
=======
                Log.i("ProjectMessageActivity",responseText);
                try {
                    jsonObject = new JSONObject(responseText);
                    JSONArray allProjectMessage = jsonObject.getJSONArray("result");;
                    for (int i=0;i<allProjectMessage.length();i++)
                    {
                        JSONObject projecyMessageObject = allProjectMessage.getJSONObject(i);
                        ProjectMessage projectMessage=new ProjectMessage();
                        projectMessage.setUserHead(projecyMessageObject.getString("head"));
                        projectMessage.setUserName(projecyMessageObject.getString("userName"));
                        projectMessage.setDonateMoney(projecyMessageObject.getDouble("donateMoney"));
                        projectMessage.setTimestamp(projecyMessageObject.getString("timestamp"));
                        projectMessagesList.add(projectMessage);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  closeProgressDialog();
                        LinearLayoutManager layoutManager = new  LinearLayoutManager(ProjectMessageActivity.this);
                        layoutManager.setStackFromEnd(true);
                        layoutManager.setReverseLayout(true);
                        recyclerView.setLayoutManager(layoutManager);
<<<<<<< HEAD
                        ProjectMessageAdapter  adapter = new ProjectMessageAdapter(hotcommentList);
=======
                        ProjectMessageAdapter  adapter = new ProjectMessageAdapter(projectMessagesList,token);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                        recyclerView.setAdapter(adapter);
                        //  show();
                    }
                });
            }
        });
    }
}

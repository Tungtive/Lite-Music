package com.DC.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.DC.android.db.Album;
import com.DC.android.db.Project;
import com.DC.android.db.RecommendSong;
import com.DC.android.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserDonateProjectActivity extends AppCompatActivity {
    private List<RecommendSong> followProjectList=new ArrayList<RecommendSong>();
    private List<RecommendSong> myProjectList=new ArrayList<RecommendSong>();
    private String token;
   // private   RecyclerView follow_project;
    private  RecyclerView my_project;
    private ProjectAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        setContentView(R.layout.activity_user_donate_project);
        Intent intent=getIntent();
        token =(String) intent.getStringExtra("token");
       // follow_project = (RecyclerView) findViewById(R.id.follow_project);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toobar);
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
        my_project = (RecyclerView) findViewById(R.id.my_project);
        initProjects(this);

    }
    private void initProjects(final Context context){
        String projectUrl="http://118.89.20.206/user/record?uid=32953014&type=1";
        HttpUtil.sendOkHttpRequest(projectUrl,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  closeProgressDialog();
                        Toast.makeText(UserDonateProjectActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                        //  show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText =response.body().string();
                Log.i("UserDonateProject",responseText);
                responseText=" {\"weekData\":[{\"playCount\":32,\"score\":100,\"song\":{\"" +
                        "rtUrls\":[],\"ar\":[{\"id\":223780,\"name\":\"陈每文\"}],\"al\":{" +
                        "\"id\":2698094,\"name\":\"热门华语186\",\"picUrl\":\"http://p3.music.126.net/PsOUIr1JyTS" +
                        "fHu3fBNQpSA==/3388694837596958.jpg\",\"pic_str\":\"3388694837596958\",\"pic\":33886" +
                        "94837596958},\"st\":2,\"v\":669,\"pst\":0,\"rtUrl\":null,\"crbt\":null,\"alia\":[],\"pop\":100.0,\"rt" +
                        "\":\"\",\"mst\":9,\"cp\":0,\"mv\":5443373,\"cf\":\"\",\"dt\":96809,\"h\":{\"br\":320000,\"fid\":579992383653" +
                        "3761,\"size\":3909811,\"vd\":-0.21},\"l\":{\"br\":96000,\"fid\":5703166813398031,\"size\":1199869,\"vd\":0.0},\"cd\":\"1\",\"no\":1,\"a\":null,\"m\":{\"br\":160000,\"fid\":5796625301650187,\"size\":1974138,\"vd\":0.2},\"fee\":0,\"ftype\":0,\"rtype\":0,\"rurl\":null,\"djId\":0,\"t\":0,\"name\":\"我的未来式\",\"id\":27937830,\"privilege\":{\"id\":27937830,\"fee\":0,\"payed\":0,\"st\":0,\"pl\":320000,\"dl\":320000,\"sp\":7,\"cp\":1,\"subp\":1,\"cs\":false,\"maxbr\":320000,\"fl\":320000,\"toast\":false,\"flag\":0,\"preSell\":false}}},{\"playCount\":4,\"score\":12,\"song\":{\"rtUrls\":[],\"ar\":[{\"id\":1081635,\"name\":\"二珂\"}],\"al\":{\"id\":36412954,\"name\":\"带着音乐去旅行\",\"picUrl\":\"http://p3.music.126.net/nRNFlT-7G28elSKWoq2-Qg==/109951163041380790.jpg\",\"pic_str\":\"109951163041380790\",\"pic\":109951163041380790},\"st\":0,\"v\":19,\"pst\":0,\"rtUrl\":null,\"crbt\":null,\"alia\":[],\"pop\":100.0,\"rt\":null,\"mst\":9,\"cp\":22036,\"mv\":5670010,\"cf\":\"\",\"dt\":265513,\"h\":{\"br\":320000,\"fid\":0,\"size\":10623521,\"vd\":0.0},\"l\":{\"br\":128000,\"fid\":0,\"size\":4249435,\"vd\":0.0},\"cd\":\"1\",\"no\":3,\"a\":null,\"m\":{\"br\":192000,\"fid\":0,\"size\":6374130,\"vd\":0.0},\"fee\":0,\"ftype\":0,\"rtype\":0,\"rurl\":null,\"djId\":0,\"t\":0,\"name\":\"三角题\",\"id\":507116696,\"privilege\":{\"id\":507116696,\"fee\":0,\"payed\":0,\"st\":0,\"pl\":320000,\"dl\":320000,\"sp\":7,\"cp\":1,\"subp\":1,\"cs\":false,\"maxbr\":999000,\"fl\":320000,\"toast\":false,\"flag\":0,\"preSell\":false}}},{\"playCount\":3,\"score\":9,\"song\":{\"rtUrls\":[],\"ar\":[{\"id\":12085016,\"name\":\"接个吻，开一枪\"},{\"id\":1203033,\"name\":\"SaMZIng\"}],\"al\":{\"id\":35258839,\"name\":\"烟袋斜街\",\"picUrl\":\"http://p4.music.126.net/tTRaVql9l0ZwQ2Y3oSZ9pQ==/109951162874838750.jpg\",\"pic_str\":\"109951162874838750\",\"pic\":109951162874838750},\"st\":0,\"v\":25,\"pst\":0,\"rtUrl\":null,\"crbt\":null,\"alia\":[],\"pop\":100.0,\"rt\":null,\"mst\":9,\"cp\":0,\"mv\":0,\"cf\":\"\",\"dt\":213600,\"h\":{\"br\":320000,\"fid\":18576248953121920,\"size\":8546264,\"vd\":0.0},\"l\":{\"br\":96000,\"fid\":18986366788468425,\"size\":2563911,\"vd\":0.0},\"cd\":\"1\",\"no\":1,\"a\":null,\"m\":{\"br\":160000,\"fid\":18921495602429071,\"size\":4273155,\"vd\":0.176469},\"fee\":0,\"ftype\":0,\"rtype\":0,\"rurl\":null,\"djId\":0,\"t\":0,\"name\":\"烟袋斜街\",\"id\":464863017,\"privilege\":{\"id\":464863017,\"fee\":0,\"payed\":0,\"st\":0,\"pl\":320000,\"dl\":320000,\"sp\":7,\"cp\":1,\"subp\":1,\"cs\":false,\"maxbr\":999000,\"fl\":320000,\"toast\":false,\"flag\":0,\"preSell\":false}}},{\"playCount\":3,\"score\":9,\"song\":{\"rtUrls\":[],\"ar\":[{\"id\":1081635,\"name\":\"二珂\"}],\"al\":{\"id\":3161278,\"name\":\"走在冷风中\",\"picUrl\":\"http://p3.music.126.net/iAaRt_l_ussYqkLVuDEpqg==/2931298001578529.jpg\",\"pic_str\":\"2931298001578529\",\"pic\":2931298001578529},\"st\":0,\"v\":7,\"pst\":0,\"rtUrl\":null,\"crbt\":null,\"alia\":[\"原唱：刘思涵\"],\"pop\":100.0,\"rt\":null,\"mst\":9,\"cp\":0,\"mv\":0,\"cf\":\"\",\"dt\":210000,\"h\":{\"br\":320000,\"fid\":7979155884142594,\"size\":8418874,\"vd\":-0.88},\"l\":{\"br\":96000,\"fid\":7939573465562149,\"size\":2525754,\"vd\":-0.48},\"cd\":\"1\",\"no\":1,\"a\":null,\"m\":{\"br\":160000,\"fid\":7931876884149399,\"size\":4209503,\"vd\":-0.45},\"fee\":0,\"ftype\":0,\"rtype\":0,\"rurl\":null,\"djId\":0,\"t\":0,\"name\":\"走在冷风中\",\"id\":32450986,\"privilege\":{\"id\":32450986,\"fee\":0,\"payed\":0,\"st\":0,\"pl\":320000,\"dl\":320000,\"sp\":7,\"cp\":1,\"subp\":1,\"cs\":false,\"maxbr\":320000,\"fl\":320000,\"toast\":false,\"flag\":0,\"preSell\":false}}}],\"code\":200}";
                try {
                    JSONObject jsonObject=new JSONObject(responseText);

                    JSONArray recentSongs=jsonObject.getJSONArray("weekData");


                    for (int i=0;i< recentSongs.length();i++)
                    {
                        JSONObject songObject1 = recentSongs.getJSONObject(i);
                        JSONObject songObject = songObject1.getJSONObject("song");
                        RecommendSong song=new RecommendSong();
                        RecommendSong recommendSong=new RecommendSong();
                        recommendSong.setId(songObject.getString("id"));
                        recommendSong.setName(songObject.getString("name"));
                        JSONObject albumObject=songObject.getJSONObject("al");
                        recommendSong.setAlbumName(albumObject.getString("name"));
                        JSONArray allAritists=songObject.getJSONArray("ar");

                        List<String> artistName=new ArrayList<>();
                        String st="";
                        for (int j=0;j<allAritists.length();j++)
                        {
                            JSONObject artistObject=allAritists.getJSONObject(j);
                            artistName.add(artistObject.getString("name"));
                            st=st+artistObject.getString("name")+"  ";
                        }
                        recommendSong.setReason(st+" / "+recommendSong.getAlbumName());
                        recommendSong.setAritistName(artistName);
                        recommendSong.save();
                        myProjectList.add(recommendSong);
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (myProjectList.size()>0) {
                            GridLayoutManager myLayoutManager = new GridLayoutManager(UserDonateProjectActivity.this,1);
                            my_project.setLayoutManager(myLayoutManager);
                            adapter = new ProjectAdapter(myProjectList);
                            my_project.setAdapter(adapter);
                        }
                        Toast.makeText(UserDonateProjectActivity.this,"加载成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

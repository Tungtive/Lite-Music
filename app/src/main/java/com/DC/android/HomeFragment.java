package com.DC.android;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
<<<<<<< HEAD
import android.support.design.widget.TabLayout;
=======
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.DC.android.db.News;
import com.DC.android.db.Project;
import com.DC.android.db.RecommendSong;
import com.DC.android.db.User;
import com.DC.android.util.HttpUtil;
import com.DC.android.util.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.jude.rollviewpager.RollPagerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.ProgressDialog.show;

/**
 * Created by XZJ on 2017/10/17.
 */

public class HomeFragment extends android.support.v4.app.Fragment{
    private RollPagerView mRollViewPager;//轮播图片
    private List<News> newsList=null;
    private List<RecommendSong> projectList=null;
    public  String[] newsImg;
    private ProjectAdapter adapter;//爱心项目显示适配器
    private SwipeRefreshLayout swipeRefresh;//下拉刷新控件
    private   RecyclerView recyclerView;
 //   private TextView newsTitle;
<<<<<<< HEAD
    private TabLayout mTabLayout;
    private ViewPager mViewPager1;
    private String[] tabTitle = {"推荐歌曲","推荐歌单"};//每个页面顶部标签的名字

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //获得布局
        View view = inflater.inflate(R.layout.fragment_home,container,false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRollViewPager = (RollPagerView) view.findViewById((R.id.roll_view_pager));
        newsList= DataSupport.findAll(News.class);

        mViewPager1 = (ViewPager) view.findViewById(R.id.mViewPager1);
        mTabLayout = (TabLayout) view.findViewById(R.id.mTabLayout);
        for (int i=0; i<tabTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }
            mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            //设置顶部标签指示条的颜色和选中页面时标签字体颜色
            mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#7CCD7C"));
            mTabLayout.setTabTextColors(Color.GRAY,Color.parseColor("#F44336"));
           // 这里注意的是，因为我是在fragment中创建MyFragmentStatePagerAdapter，所以要传getChildFragmentManager()
//            mViewPager1.setAdapter(new MyFragmentStatePagerAdapter(getChildFragmentManager(),tabTitle));
//            //在设置viewpager页面滑动监听时，创建TabLayout的滑动监听
//            mViewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//
//            mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                @Override
//                public void onTabSelected(TabLayout.Tab tab) {
//                    //在选中的顶部标签时，为viewpager设置currentitem
//                    mViewPager1.setCurrentItem(tab.getPosition());
//                }
//
//                @Override
//                public void onTabUnselected(TabLayout.Tab tab) {
//
//                }
//
//                @Override
//                public void onTabReselected(TabLayout.Tab tab) {
//
//                }
//            });



            if (newsList.size()>0) {

            mRollViewPager.setAnimationDurtion(500);   //设置切换时间
            mRollViewPager.setAdapter(new TestLoopAdapter(mRollViewPager, newsList)); //设置适配器
            mRollViewPager.setHintView(new ColorPointHintView(getContext(), Color.WHITE, Color.GRAY));// 设置圆点指示器颜色
            projectList= DataSupport.findAll(RecommendSong.class);
            //    if (projectList.size()==0) initProjects();//初始化爱心项目数组
            if  (projectList.size()>0)
            {
                LinearLayoutManager layoutManager = new  LinearLayoutManager(getContext());
//                            layoutManager.setStackFromEnd(true);
//                            layoutManager.setReverseLayout(true);
                recyclerView.setLayoutManager(layoutManager);
                MainActivity activity=(MainActivity)getActivity();
                activity.toolbar.setTitle("主页");
                adapter = new ProjectAdapter(projectList);
                recyclerView.setAdapter(adapter);
            }
        }else {
            initBanners();
        }
        //实例化轮播控件
=======
    private ViewPager view_pager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        //获得布局
        View view = inflater.inflate(R.layout.fragment_home,container,false);
      //  DataSupport.deleteAll(Project.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRollViewPager = (RollPagerView) view.findViewById((R.id.roll_view_pager));
  //      initProjects();
     //   newsTitle=(TextView) view.findViewById(R.id.new_title) ;


        //实例化轮播控件

>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
        //实例化下拉刷新控件
        swipeRefresh =(SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshProjects();

            }
        });

        return view;
    }
    @Override
   public  void onActivityCreated (Bundle saveInstanceState){
       super.onActivityCreated(saveInstanceState);

   }
    private  void refreshProjects(){
       // initProjects();
       //adapter.notifyDataSetChanged();// 通知数据发生了变化
        swipeRefresh.setRefreshing(false);//刷新事件结束并隐藏刷新进度条
    }
<<<<<<< HEAD
    private void initBanners(){

       String Url="http://118.89.20.206/banner";
        HttpUtil.sendOkHttpRequest(Url,new Callback() {
=======
    private void initProjects(){

       String projectUrl="http://139.199.162.139:8080/user/main";

        MainActivity activity=(MainActivity)getActivity();
        HttpUtil.sendOkHttpRequest(projectUrl, activity.TOKEN,new Callback() {
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
            @Override
            public void onFailure(Call call, IOException e) {
                if  (getActivity() == null) return;  //getActivity()有可能为空 防止空指针异常
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                           Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();

                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText =response.body().string();
                Log.i("MainActivity",responseText);
<<<<<<< HEAD
                boolean result=  Utility.handleBannerResponce(responseText);
=======
                boolean result=  Utility.handleProjectResponce(responseText);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                if  (getActivity() == null) return;  //getActivity()有可能为空 防止空指针异常
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  closeProgressDialog();
                      //  Toast.makeText(getContext(),"加载成功",Toast.LENGTH_SHORT).show();
<<<<<<< HEAD
                        newsList= DataSupport.findAll(News.class);
                        if (newsList.size()>0) {
=======
                        projectList= DataSupport.findAll(Project.class);
                        //    if (projectList.size()==0) initProjects();//初始化爱心项目数组
                        if  (projectList.size()>0)
                        {
                            LinearLayoutManager layoutManager = new  LinearLayoutManager(getContext());
                            layoutManager.setStackFromEnd(true);
                            layoutManager.setReverseLayout(true);
                            recyclerView.setLayoutManager(layoutManager);
                            MainActivity activity=(MainActivity)getActivity();
                            activity.toolbar.setTitle("主页");
                            adapter = new ProjectAdapter(projectList,activity.TOKEN);
                            recyclerView.setAdapter(adapter);
                        }
                        newsList= DataSupport.findAll(News.class);
                        if (newsList.size()>0) {

>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                            mRollViewPager.setAnimationDurtion(500);   //设置切换时间
                            mRollViewPager.setAdapter(new TestLoopAdapter(mRollViewPager,newsList)); //设置适配器
                            mRollViewPager.setHintView(new ColorPointHintView(getContext(), Color.WHITE, Color.GRAY));// 设置圆点指示器颜色
                            // mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
                        }
<<<<<<< HEAD
                         initRecommendSongs();
                        //  show();
                    }
                });
            }
        });
    }


    private void initRecommendSongs(){
=======
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626

        String Url="http://118.89.20.206/recommend/songs";
        HttpUtil.sendOkHttpRequest(Url,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if  (getActivity() == null) return;  //getActivity()有可能为空 防止空指针异常
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();

                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText =response.body().string();
                Log.i("recommendsongs",responseText);
                boolean result=  Utility.handleRecommendSongResponce(responseText);
                if  (getActivity() == null) return;  //getActivity()有可能为空 防止空指针异常
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        projectList= DataSupport.findAll(RecommendSong.class);
                        //    if (projectList.size()==0) initProjects();//初始化爱心项目数组
                        if  (projectList.size()>0)
                        {
                            LinearLayoutManager layoutManager = new  LinearLayoutManager(getContext());
//                            layoutManager.setStackFromEnd(true);
//                            layoutManager.setReverseLayout(true);
                            recyclerView.setLayoutManager(layoutManager);
                            MainActivity activity=(MainActivity)getActivity();
                            activity.toolbar.setTitle("主页");
                            adapter = new ProjectAdapter(projectList);
                            recyclerView.setAdapter(adapter);
                        }
                        //  show();
                    }
                });
            }
        });
    }

    private class TestLoopAdapter extends LoopPagerAdapter {
        private int[] imgs = {R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,};  // 本地图片
        private int count = newsList.size();  // banner上图片的数量

        public TestLoopAdapter(RollPagerView viewPager,  List<News> newsList) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            final int picNo = position + 1;

            ImageView view = new ImageView(container.getContext());
            //view.setImageResource(imgs[position]);
            final News news=newsList.get(position);
<<<<<<< HEAD
            Glide.with(getContext()).load(news.getImgUrl()).into(view);
=======
            Glide.with(getContext()).load(getResources().getString(R.string.server_add)+news.getImgUrl()).into(view);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
            Log.i("newstitle",news.getImgUrl());
            Log.i("newstitle",news.getNewsDetail());

            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            view.setOnClickListener(new View.OnClickListener()      // 点击事件
            {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "点击了第" + picNo + "张图片", Toast.LENGTH_SHORT).show();
         //           newsTitle.setText(news.getNewsDetail());
                 //   Intent intent = new Intent(getContext(),ProjectActivity.class);
                  //  intent.putExtra("projectName",project.getProjectName());
                   // intent.putExtra("projectImgID",project.getImg());
                //    getContext().startActivity(intent);
                }

            });
            return view;
        }
        @Override
        public int getRealCount() {
            return count;
        }

    }

<<<<<<< HEAD


=======
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
}

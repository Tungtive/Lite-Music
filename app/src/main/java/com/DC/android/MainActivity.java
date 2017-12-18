package com.DC.android;


import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.DC.android.db.User;
import com.DC.android.util.HttpUtil;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;

import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.view.KeyEvent.KEYCODE_BACK;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private BottomNavigationBar bottomNavigationBar;

    private PlanFragment mPlanFragment;
    private HomeFragment mHomeFragment;
   public User user=new User();
    private NavigationView navView;
    public   String TOKEN;
    public Toolbar toolbar;
    private String fragmengtName=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar

        Intent intent=getIntent();
        clearImageAllCache(MainActivity.this);
        TOKEN=intent.getStringExtra("token");
        fragmengtName=intent.getStringExtra("fragmentName");
        LitePal.initialize(this);//初始化数据库
        setContentView(R.layout.activity_main);
       // initUser();
         toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_wallet);
//        navView.setCheckedItem(R.id.nav_donate_project);
//        navView.setCheckedItem(R.id.nav_my_project);
//        navView.setCheckedItem(R.id.nav_star_project);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
       if ("plan".equals(fragmengtName))
           replaceFragment(new PlanFragment());
        else  replaceFragment(new HomeFragment());
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // mDrawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.nav_me :  {
<<<<<<< HEAD
                                      Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                  //    Bundle bundle = new Bundle();
                                   //   bundle.putSerializable("user",user);
                                   //   intent.putExtras(bundle);
=======
                                      Intent intent = new Intent(MainActivity.this,MeActivity.class);
                                      Bundle bundle = new Bundle();
                                      bundle.putSerializable("user",user);
                                      intent.putExtras(bundle);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                                      startActivity(intent);
                                      break;}
                    case R.id.nav_wallet:{

                        Intent intent = new Intent(MainActivity.this,WalletActivity.class);
                        intent.putExtra("money",user.getBalance());
                        intent.putExtra("value",user.getValue());
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_donate_project:{
                        Intent intent = new Intent(MainActivity.this, UserDonateProjectActivity.class);
                        intent.putExtra("token",TOKEN);
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_star_project:{
                        Intent intent = new Intent(MainActivity.this, UserStarProjectActivity.class);
                        intent.putExtra("token",TOKEN);
                        startActivity(intent);
                        break;
                    }
                    case R.id.nav_my_project:{
                        Intent intent = new Intent(MainActivity.this, UserCreateProjectActivity.class);
                        intent.putExtra("token",TOKEN);
                        startActivity(intent);
                        break;
                    }
                    default:break;
                }
                    return true;
            }
        });

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation);
        bottomNavigationBar
                .setActiveColor("#ffffff")
                .setInActiveColor("#FFFFFF")
                .setBarBackgroundColor("#F44336");

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_button_house, "Home"))
              .addItem(new BottomNavigationItem(R.drawable.ic_search, "Search"))
                .addItem(new BottomNavigationItem(R.drawable.ic_song_list, "List"))
             .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                    { replaceFragment(new HomeFragment()); break;}
                    case 1:
                    { replaceFragment(new UserMessageFragment()); break;}
                    case 2:
                    { replaceFragment(new AddFragment()); break;}
                    default:break;
                }
                }
                @Override
                public void onTabUnselected(int position) {
                }
            @Override
            public void onTabReselected(int position) {
            }}
        );

    }

    private  void  initUser(){
        String Url="http://139.199.162.139:8080/user/main";
        HttpUtil.sendOkHttpRequest(Url,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //  closeProgressDialog();
                        Toast.makeText(MainActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                        //  show();
                    }
                });
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText =response.body().string();
                Log.i("MainActivity",responseText);
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject = new JSONObject(responseText);
                    JSONObject jsonObjectUser = jsonObject.getJSONObject("user");;
                    user.setHead(jsonObjectUser.getString("head"));
                    user.setGender(jsonObjectUser.getString("gender"));
                    user.setName(jsonObjectUser.getString("name"));
                    user.setRegion(jsonObjectUser.getString("region"));
                    user.setEmail(jsonObjectUser.getString("email"));
                    user.setBalance(jsonObjectUser.getDouble("balance"));
                    user.setValue(jsonObjectUser.getDouble("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        View  navHeadView =(View) navView.getHeaderView(navView.getHeaderCount()-1);
                        TextView nav_mail=(TextView) navHeadView.findViewById(R.id.nav_mail);
                        TextView nav_username=(TextView) navHeadView.findViewById(R.id.nav_userName);
                        CircleImageView nav_userHead=(CircleImageView) navHeadView.findViewById(R.id.nav_user_head);
                        nav_mail.setText(user.getEmail());
                        nav_username.setText(user.getName());
                        Glide.with(MainActivity.this).load("http://139.199.162.139:8080"+user.getHead()).into(nav_userHead);
                    }
                });
            }
        });

    }
   public void replaceFragment(android.support.v4.app.Fragment fragment ) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_layout,fragment);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
<<<<<<< HEAD
               // initUser();
=======
                initUser();
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                mDrawerLayout.openDrawer(GravityCompat.START);
            break;
            default:
        }
        return true;
    }




    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
        String ImageExternalCatchDir=context.getExternalCacheDir()+ ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
        //deleteFolderFile(ImageExternalCatchDir, true);
    }/**
     * 清除图片磁盘缓存
     */
    public void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(context).clearDiskCache();
// BusUtil.getBus().post(new GlideCacheClearSuccessEvent());
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



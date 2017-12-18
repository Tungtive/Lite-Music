package com.DC.android;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.DC.android.db.Plan;
import com.DC.android.db.Project;
import com.DC.android.db.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeActivity extends AppCompatActivity {
    private com.DC.android.MyScrollView myScrollView;
     private List<User> userList =new ArrayList<>();
    private UserInfoAdapter adapter;
    private User user;
    private EditText email_edit;
    private EditText name_edit;
    private EditText region_edit;
    private    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        setContentView(R.layout.activity_me);
        Intent intent=getIntent();
        user=(User) intent.getSerializableExtra("user");
        Toolbar toolbar =(Toolbar)findViewById(R.id.toobar);
        myScrollView = (MyScrollView) findViewById(R.id.personalScrollView);
        name_edit=(EditText) findViewById(R.id.name_edit);
        region_edit=(EditText) findViewById(R.id.region_edit);
        email_edit=(EditText) findViewById(R.id.email_edit);
        group = (RadioGroup) findViewById(R.id.radioGroup);
        email_edit.setHint(user.getEmail());
        name_edit.setHint(user.getName());
        region_edit.setHint(user.getRegion());
      if (user.getGender().equals("man")) group.check(R.id.male);
         else  group.check(R.id.female);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        CircleImageView circleImageView=(CircleImageView) findViewById(R.id.image_header) ;
        Glide.with(this).load("http://139.199.162.139:8080"+user.getHead()).into(circleImageView);
      //  Toolbar toolbar =(Toolbar)findViewById(R.id.toobar);
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
}

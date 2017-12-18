package com.DC.android;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import static com.DC.android.R.menu.toolbar;

public class WalletActivity extends AppCompatActivity {
     private double balance;
    private double value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏actionbar
        setContentView(R.layout.activity_wallet);
        Intent intent=getIntent();
        balance =(double) intent.getDoubleExtra("money",balance);
        value=(double) intent.getDoubleExtra("value",value);
        TextView  textBalace=(TextView) findViewById(R.id.text_balance);
        TextView  textValue=(TextView) findViewById(R.id.text_value);
        textBalace.setText(Double.toString(balance));
        textValue.setText(Double.toString(value));
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

    }
}

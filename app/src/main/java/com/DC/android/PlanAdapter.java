package com.DC.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.DC.android.db.Plan;
import com.DC.android.db.Project;
import com.DC.android.util.HttpUtil;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by XZJ on 2017/10/15.
 */
//recycleView 适配器
public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    private Context mContext;
    private List<Plan> mPlanList;
    private String token;
    private Plan plan;

    class ViewHolder extends RecyclerView.ViewHolder {
        // CardView cardView;
        View planView;
        ImageView planImage;
        TextView planName;
        //     TextView planTotalDays;
        TextView planFinshedTimes;

        //     TextView planValue;
        public ViewHolder(View view) {
            super(view);
            planView = view;
            planImage = (ImageView) view.findViewById(R.id.plan_image);
            planName = (TextView) view.findViewById(R.id.plan_name);
            //     planTotalDays = (TextView) view.findViewById(R.id.plan_totalDays);
            planFinshedTimes = (TextView) view.findViewById(R.id.plan_finishedTimes);
            //     planValue = (TextView) view.findViewById(R.id.plan_value);

        }
    }

    public PlanAdapter(List<Plan> planList, String token) {
        mPlanList = planList;
        this.token = token;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.plan_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.planView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                plan = mPlanList.get(position);
               // initclock() ;
                Intent intent = new Intent(mContext, PlanActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("plan", plan);
                intent.putExtra("token", token);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
        return holder;
        //new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Plan plan = mPlanList.get(position);
        holder.planName.setText(plan.getPlanName());
        // Glide.with(mContext).load("ic_button_chart").into(holder.planImage);
        //   holder.planTotalDays.setText(plan.getTotalDays());
        holder.planFinshedTimes.setText(plan.getFinishedTimes() + "天");
        //  holder.planValue.setText(plan.getValue());
    }

    @Override
    public int getItemCount() {
        return mPlanList.size();
    }
    //判断今天是否已经打卡



}
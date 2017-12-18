package com.DC.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.DC.android.db.Plan;
import com.DC.android.db.Project;
import com.DC.android.db.User;
import com.bumptech.glide.Glide;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by XZJ on 2017/10/15.
 */
//recycleView 适配器
public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.ViewHolder> {
    private Context mContext;
    private List<User> mUserList;
    class ViewHolder extends RecyclerView.ViewHolder {
        View meView;
        ImageView image_me;
        ImageView image_big;
        TextView text_me;
        //     TextView planTotalDays;
        TextView planFinshedTimes;
        //     TextView planValue;
        public ViewHolder(View view) {
            super(view);
            meView=view;
            image_me = (ImageView) view.findViewById(R.id.image_me);
            image_big= (ImageView) view.findViewById(R.id.big);
            text_me = (TextView) view.findViewById(R.id.text_me);
        }
    }
    public UserInfoAdapter(List<User> userList) {mUserList = userList;}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (mContext ==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.me_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.meView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
               /* Plan plan =mPlanList.get(position);
                Intent intent = new Intent(mContext,PlanActivity.class);
                intent.putExtra(PlanActivity.PLAN_NAME,plan.getPlanName());
                intent.putExtra(PlanActivity.PLAN_FINISHED_TIMES,plan.getFinishedTimes());
                intent.putExtra(PlanActivity.PLAN_TOTAL_DAYS,plan.getTotalDays());
                intent.putExtra(PlanActivity.PLAN_VALUE,plan.getValue());
                intent.putExtra(PlanActivity.PLAN_DATE,plan.getDate());
                mContext.startActivity(intent);*/
            }
        });
        return holder;
        //new ViewHolder(view);
    }
    @Override
    public  void onBindViewHolder(ViewHolder holder,int position){
        User user=mUserList.get(position);
        holder.text_me.setText(user.getName());
        Glide.with(mContext).load(user.getHead()).into(holder.image_me);
        Glide.with(mContext).load(R.drawable.ic_right_arrow).into(holder.image_big);
    }
    @Override
    public int getItemCount(){
        return mUserList.size();
    }
}
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

import com.DC.android.db.Plan;
import com.DC.android.db.Project;
import com.DC.android.db.UserMessage;
import com.bumptech.glide.Glide;

import java.security.PublicKey;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by XZJ on 2017/10/15.
 */
//recycleView 适配器
public class UserMessageAdapter extends RecyclerView.Adapter<UserMessageAdapter.ViewHolder> {
    private Context mContext;
    private List<UserMessage> mUserMessageList;
    private String token;
    class ViewHolder extends RecyclerView.ViewHolder {
        // CardView cardView;
        View mUserMessageView;
        TextView userName;
        TextView message;
        TextView projectName;
        CircleImageView userHead;
        //     TextView planValue;
        public ViewHolder(View view) {
            super(view);
            mUserMessageView = view;
            userName = (TextView) view.findViewById(R.id.user_name);
             projectName = (TextView) view.findViewById(R.id.project_name);
           message = (TextView) view.findViewById(R.id.message);
            userHead=(CircleImageView)view.findViewById(R.id.user_head);

        }
    }
    public UserMessageAdapter(List<UserMessage> userMessageList,String token)
    {    mUserMessageList = userMessageList;
        this.token=token;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (mContext ==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_message_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mUserMessageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                int position = holder.getAdapterPosition();
//                Plan plan =mPlanList.get(position);
//                Intent intent = new Intent(mContext,PlanActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("plan",plan);
//                intent.putExtra("token",token);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
            }
        });
        return holder;
        //new ViewHolder(view);
    }
    @Override
    public  void onBindViewHolder(ViewHolder holder,int position){
        UserMessage userMessage=mUserMessageList.get(position);
        holder.userName.setText(userMessage.getName());
        holder.projectName.setText("捐助了："+userMessage.getProjectName());
        holder.message.setText(userMessage.getMessage());
        Glide.with(mContext).load("http://139.199.162.139:8080"+userMessage.getHead()).into(holder.userHead);
        //  holder.planValue.setText(plan.getValue());
    }
    @Override
    public int getItemCount(){
        return mUserMessageList.size();
    }
}
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

<<<<<<< HEAD
import com.DC.android.db.Hotcomment;
import com.DC.android.db.Plan;
import com.DC.android.db.Project;
import com.DC.android.db.ProjectMessage;
import com.DC.android.db.RecommendSong;
=======
import com.DC.android.db.Plan;
import com.DC.android.db.Project;
import com.DC.android.db.ProjectMessage;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
import com.DC.android.db.UserMessage;
import com.bumptech.glide.Glide;

import java.security.PublicKey;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by XZJ on 2017/10/15.
 */
//recycleView 适配器
public class ProjectMessageAdapter extends RecyclerView.Adapter<ProjectMessageAdapter.ViewHolder> {
    private Context mContext;
<<<<<<< HEAD
    private List<Hotcomment> mHotCommentList;
    private String token;
    class ViewHolder extends RecyclerView.ViewHolder {
        View mHotCommentView;
        TextView userName;
        TextView timestamp;
        TextView likedCount;
        TextView comment;
        ImageView likeImg;
=======
    private List<ProjectMessage> mProjectMessageList;
    private String token;
    class ViewHolder extends RecyclerView.ViewHolder {
        // CardView cardView;
        View mProjectMessageView;
        TextView userName;
        TextView timestamp;
        TextView donateMoney;
        TextView value;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
        CircleImageView userHead;
        //     TextView planValue;
        public ViewHolder(View view) {
            super(view);
<<<<<<< HEAD
            mHotCommentView = view;
            userName = (TextView) view.findViewById(R.id.user_name);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
            likedCount = (TextView) view.findViewById(R.id.liked_count);
            comment=(TextView)view.findViewById(R.id.comment);
            likeImg=(ImageView)view.findViewById(R.id.liked_img);
=======
            mProjectMessageView = view;
            userName = (TextView) view.findViewById(R.id.user_name);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
            value = (TextView) view.findViewById(R.id.value);
            donateMoney=(TextView)view.findViewById(R.id.donate_money);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
            userHead=(CircleImageView)view.findViewById(R.id.user_head);

        }
    }
<<<<<<< HEAD
    public  ProjectMessageAdapter(List<Hotcomment>  hotCommentList)
    {    mHotCommentList =  hotCommentList;
=======
    public  ProjectMessageAdapter(List<ProjectMessage>  ProjectMessageList,String token)
    {    mProjectMessageList =  ProjectMessageList;
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
        this.token=token;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (mContext ==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.project_message_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
<<<<<<< HEAD
        holder.mHotCommentView.setOnClickListener(new View.OnClickListener(){
=======
        holder.mProjectMessageView.setOnClickListener(new View.OnClickListener(){
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
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
<<<<<<< HEAD
        Hotcomment hotcomment=mHotCommentList.get(position);
        holder.userName.setText(hotcomment.getUserNickName());
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(Long.parseLong(hotcomment.getTime()));
        holder.timestamp.setText(time);
        holder.comment.setText(hotcomment.getContent());
        holder.likedCount.setText(hotcomment.getLikedCount()+"");
        holder.likeImg.setImageResource(R.drawable.ic_star);
        Glide.with(mContext).load(hotcomment.getUserAvaturUrl()).into(holder.userHead);
=======
        ProjectMessage projectMessage=mProjectMessageList.get(position);
        holder.userName.setText(projectMessage.getUserName()+" 支持了:  ");
        holder.donateMoney.setText(projectMessage.getDonateMoney()+"");
        holder.value.setText(" "+Double.toString(projectMessage.getDonateMoney()));
        holder.timestamp.setText(projectMessage.getTimestamp());
        Glide.with(mContext).load("http://139.199.162.139:8080"+projectMessage.getUserHead()).into(holder.userHead);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
        //  holder.planValue.setText(plan.getValue());
    }
    @Override
    public int getItemCount(){
<<<<<<< HEAD
        return mHotCommentList.size();
=======
        return mProjectMessageList.size();
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
    }
}
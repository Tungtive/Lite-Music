package com.DC.android;

import android.app.Activity;
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

import com.DC.android.db.Project;
import com.DC.android.db.RecommendSong;
import com.bumptech.glide.Glide;
import com.daimajia.numberprogressbar.NumberProgressBar;

import java.security.PublicKey;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by XZJ on 2017/10/15.
 */
//recycleView 适配器
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private Context mContext;
    private List<RecommendSong> mProjectList;
    private int layoutId;
    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView songName;
        TextView songInfo;
        TextView songPosition;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            songName = (TextView) view.findViewById(R.id.song_name);
            songInfo=(TextView) view.findViewById(R.id.song_info);
            songPosition=(TextView) view.findViewById(R.id.song_position);
        }
    }

    public ProjectAdapter(List<RecommendSong> projectList) {
        mProjectList = projectList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (mContext ==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.project_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                RecommendSong song =mProjectList.get(position);
                Intent intent = new Intent(mContext,ProjectActivity.class);
                Bundle bundle = new Bundle();
<<<<<<< HEAD
                bundle.putSerializable("song",song);
=======
                bundle.putSerializable("project",project);
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return holder;
                //new ViewHolder(view);
    }
    @Override
    public  void onBindViewHolder(ViewHolder holder,int position){
       RecommendSong song=mProjectList.get(position);
        holder.songName.setText(song.getName());
        holder.songInfo.setText(song.getReason());
        holder.songPosition.setText(position+"");
    }
    @Override
    public int getItemCount(){
        return mProjectList.size();
    }
}
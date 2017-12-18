package com.DC.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.DC.android.db.Project;
import com.bumptech.glide.Glide;
import com.daimajia.numberprogressbar.NumberProgressBar;

import java.security.PublicKey;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by XZJ on 2017/10/15.
 */
//recycleView 适配器
public class ProjectPhotoSetAdaper extends RecyclerView.Adapter< ProjectPhotoSetAdaper.ViewHolder> {
    private Context mContext;
    private List<String> mList;

    class ViewHolder extends RecyclerView.ViewHolder {
        View photoView;
        ImageView projectImage;
        public ViewHolder(View view) {
            super(view);
            photoView=view;
            projectImage = (ImageView) view.findViewById(R.id.project_photo_set_item);
        }
    }

    public  ProjectPhotoSetAdaper(List<String> List) {
        mList = List;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (mContext ==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.project_photo,parent,false);
        final ViewHolder holder = new ViewHolder(view);
//        holder.photoView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                int position = holder.getAdapterPosition();
//                 =mList.get(position);
//                Intent intent = new Intent(mContext,ProjectActivity.class);
////                intent.putExtra("projectName",project.getProjectName());
////                intent.putExtra("projectImgID",project.getImg());
////                intent.putExtra("projectDescription",project.getDescription());
////                intent.putExtra("projectDetail",project.getDetail());
////                intent.putExtra("projectCurrentMoney",project.getCurrentMoney());
////                intent.putExtra()
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("project",project);
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
 //           }
 //       });
        return holder;
        //new ViewHolder(view);
    }
    @Override
    public  void onBindViewHolder(ViewHolder holder,int position){
        Glide.with(mContext).load("http://139.199.162.139:8080/img/project/"+mList.get(position)+".jpg").into(holder.projectImage);

    }
    @Override
    public int getItemCount(){
        return mList.size();
    }
}
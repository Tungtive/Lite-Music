package com.DC.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.DC.android.db.Album;
import com.DC.android.db.News;
import com.DC.android.db.Plan;
import com.DC.android.db.Project;
import com.DC.android.db.RecommendSong;
import com.DC.android.db.User;
import com.DC.android.db.UserMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XZJ on 2017/11/1.
 */

public class Utility {

    /**
     * 解析和处理服务器返回的project数据
     */
    //public List<Project> projectList =new ArrayList<>();
    public   static boolean handleBannerResponce(String responce){
        if (!TextUtils.isEmpty(responce)){
            try {
                 JSONObject jsonObject=new JSONObject(responce);
<<<<<<< HEAD
                 JSONArray allNews=jsonObject.getJSONArray("banners");
                  DataSupport.deleteAll(News.class);
=======
                 JSONArray allProjects=jsonObject.getJSONArray("projects");
                 JSONArray allNews=jsonObject.getJSONArray("news");
                 JSONObject jsonObjectUser = jsonObject.getJSONObject("user");
                  DataSupport.deleteAll(Project.class);
                  DataSupport.deleteAll(News.class);
                 for (int i=0;i<allProjects.length();i++)
                 {
                     JSONObject projectObject = allProjects.getJSONObject(i);
                     Project project=new Project();
                     project.setProjectName(projectObject.getString("projectName"));
                     project.setCurrentMoney(projectObject.getDouble("currentMoney"));
                     project.setTargetMoney(projectObject.getDouble("targetMoney"));
                     project.setDescription(projectObject.getString("description"));
                     project.setDetail(projectObject.getString("detail"));
                     project.setImg(projectObject.getString("img"));
                      project.setInitiatorName(projectObject.getString("initiatorName"));
                     project.setPeopleNum(projectObject.getInt("peopleNumber"));
                     project.setStartTime(projectObject.getString("startTime"));
                     if (projectObject.has("imgListStr")) project.setImgListStr(projectObject.getString("imgListStr"));
                     project.setPid(projectObject.getInt("id"));
                     project.save();
                 }
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                for (int i=0;i<allNews.length();i++)
                {
                    JSONObject newsObject = allNews.getJSONObject(i);
                     News news=new News();
<<<<<<< HEAD
                     news.setImgUrl(newsObject.getString("pic"));
                    news.setNewsDetail(newsObject.getString("typeTitle"));
=======
                     news.setImgUrl(newsObject.getString("imgUrl"));
                    news.setNewsDetail(newsObject.getString("newsDetail"));
>>>>>>> 7d9c07a30238245008c650f3d764d709944b6626
                  //  news.setTitle(newsObject.getString(""));
                    news.save();
               }

               return true;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
       return false;
    }
    public   static boolean handleRecommendSongResponce(String responce){
        if (!TextUtils.isEmpty(responce)){
            try {
                JSONObject jsonObject=new JSONObject(responce);
                JSONArray allSongs=jsonObject.getJSONArray("recommend");
                DataSupport.deleteAll(RecommendSong.class);
                for (int i=0;i<allSongs.length();i++)
                {
                    JSONObject songObject = allSongs.getJSONObject(i);
                    RecommendSong recommendSong=new RecommendSong();
                    recommendSong.setId(songObject.getString("id"));
                    recommendSong.setName(songObject.getString("name"));
                    recommendSong.setReason(songObject.getString("reason"));
                    recommendSong.setPosition(songObject.getInt("position"));
                    JSONObject albumObject=songObject.getJSONObject("album");
                    Album album=new Album();
                    album.setName(albumObject.getString("name"));
                    album.setCompany(albumObject.getString("company"));
                    album.setPicUrl(albumObject.getString("picUrl"));
                    recommendSong.setAlbumPicUrl(albumObject.getString("picUrl"));
                    recommendSong.setAlbumCompany(albumObject.getString("company"));
                    recommendSong.setAlbumName(albumObject.getString("name"));
                    recommendSong.setAlbum(album);
                    recommendSong.setAlbumPublishTime(albumObject.getString("publishTime"));
                    recommendSong.setAlbumType(albumObject.getString("type"));
                    JSONArray allAritists=songObject.getJSONArray("artists");
                    List<String> artistName=new ArrayList<>();
                    for (int j=0;j<allAritists.length();j++)
                    {
                        JSONObject artistObject=allAritists.getJSONObject(j);
                        artistName.add(artistObject.getString("name"));
                    }
                    recommendSong.setAritistName(artistName);

                    recommendSong.save();
                }

                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }

    public   static boolean handlePlanResponce(String responce){
        if (!TextUtils.isEmpty(responce)){
            try {
                JSONObject jsonObject=new JSONObject(responce);
                JSONArray allPlans=jsonObject.getJSONArray("plans");
                DataSupport.deleteAll(Plan.class);
                for (int i=0;i<allPlans.length();i++)
                {
                    JSONObject planObject = allPlans.getJSONObject(i);
                    Plan plan=new Plan();
                    plan.setPlanName(planObject.getString("planName"));
                    plan.setFinishedTimes(planObject.getString("finishedTimes"));
                    plan.setDeadline(planObject.getString("deadline"));
                    plan.setValue(planObject.getString("value"));
                    plan.setStartDate(planObject.getString("startDate"));
                    plan.setPlamId(planObject.getInt("id"));
                    if (planObject.has("lastClock"))  plan.setLastClock(planObject.getString("lastClock"));
                    //     project.setInitiatorName(projectObject.getString("initiator_name"));
                    //projectList.add(project);
                    plan.save();
                }

                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
        return false;
    }
    public   static boolean handleMessageResponce(String responce){
        if (!TextUtils.isEmpty(responce)){
            JSONObject jsonObject=new JSONObject();
            Log.i("MainActivity",responce);
            DataSupport.deleteAll(UserMessage.class);
            try {
                jsonObject = new JSONObject(responce);
                JSONArray userMessageArray = jsonObject.getJSONArray("userMessage");;
                for (int i=0;i<=userMessageArray.length()-1;i++) {
                    UserMessage userMessage=new UserMessage();
                    JSONObject  jsonObjectUser=userMessageArray.getJSONObject(i);
                    userMessage.setHead(jsonObjectUser.getString("head"));
                    userMessage.setName(jsonObjectUser.getString("name"));
                    userMessage.setTimeStamp(jsonObjectUser.getString("timestamp"));
                    userMessage.setMessage(jsonObjectUser.getString("message"));
                    userMessage.setProjectName(jsonObjectUser.getString("projectName"));
                    userMessage.save();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

}


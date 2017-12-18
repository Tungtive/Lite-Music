package com.DC.android.db;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XZJ on 2017/12/17.
 */

public class RecommendSong extends DataSupport implements Serializable {
    private  String name;
    private  String sId;
    private  int position;
    private  String Starred;
    private  int popularity;
    private String reason;
    private List<Aritist> aritistList;
    private Album album;
    private String albumPicUrl;
    private String albumName;
    private String albumCompany;
    private String albumPublishTime;
    private String albumType;
    private List<String> AritistName;

    public List<String> getAritistName() {
        return AritistName;
    }

    public void setAritistName(List<String> aritistName) {
        AritistName = aritistName;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public String getAlbumPublishTime() {
        return albumPublishTime;
    }

    public void setAlbumPublishTime(String albumPublishTime) {
        this.albumPublishTime = albumPublishTime;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumCompany() {
        return albumCompany;
    }

    public void setAlbumCompany(String albumCompany) {
        this.albumCompany = albumCompany;
    }

    public String getAlbumPicUrl() {
        return albumPicUrl;
    }

    public void setAlbumPicUrl(String albumPicUrl) {
        this.albumPicUrl = albumPicUrl;
    }

    public List<Aritist> getAritistList() {
        return aritistList;
    }

    public void setAritistList(List<Aritist> aritistList) {
        this.aritistList = aritistList;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public RecommendSong() {
    }

    public String getStarred() {
        return Starred;
    }

    public void setStarred(String starred) {
        Starred = starred;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RecommendSong(String name, String id, int position) {
        this.name = name;
        this.sId = id;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return sId;
    }

    public void setId(String id) {
        this.sId = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

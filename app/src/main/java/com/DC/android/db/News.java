package com.DC.android.db;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

import org.litepal.crud.DataSupport;

/**
 * Created by huangzhenyang on 2017/9/7.
 */
//@Entity
public class News  extends DataSupport {
   // @Id
  //  @GeneratedValue
    private Integer id;

    private String title; // 新闻标题
    private String imgUrl; // 首页显示的图片的链接
    private String newsDetail; // 新闻内容

    //构造器
    public News() {
    }

    public News(String title, String imgUrl, String newsDetail) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.newsDetail = newsDetail;
    }

    //setter and getter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    // to string
    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", newsDetail='" + newsDetail + '\'' +
                '}';
    }
}

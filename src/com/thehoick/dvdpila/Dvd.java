package com.thehoick.dvdpila;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dvd {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rating")
    @Expose
    private Object rating;
    @SerializedName("abstract_txt")
    @Expose
    private String abstractTxt;
    @SerializedName("abstract_source")
    @Expose
    private String abstractSource;
    @SerializedName("abstract_url")
    @Expose
    private String abstractUrl;
    @SerializedName("file_url")
    @Expose
    private String fileUrl;
    @SerializedName("playback_time")
    @Expose
    private Integer playbackTime;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("created_at")
    @Expose
    private Date createdAt;
    @SerializedName("url")
    @Expose
    private String url;

    @Override
    public String toString() {
        return "Dvd{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", fileUrl='" + fileUrl + '\'' +
                ", playbackTime=" + playbackTime +
                ", imageUrl='" + imageUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

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

    public Object getRating() {
        return rating;
    }

    public void setRating(Object rating) {
        this.rating = rating;
    }

    public String getAbstractTxt() {
        return abstractTxt;
    }

    public void setAbstractTxt(String abstractTxt) {
        this.abstractTxt = abstractTxt;
    }

    public String getAbstractSource() {
        return abstractSource;
    }

    public void setAbstractSource(String abstractSource) {
        this.abstractSource = abstractSource;
    }

    public String getAbstractUrl() {
        return abstractUrl;
    }

    public void setAbstractUrl(String abstractUrl) {
        this.abstractUrl = abstractUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getPlaybackTime() {
        return playbackTime;
    }

    public void setPlaybackTime(Integer playbackTime) {
        this.playbackTime = playbackTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}


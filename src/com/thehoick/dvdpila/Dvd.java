
package com.thehoick.dvdpila;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dvd {
    @Override
    public String toString() {
        return "Dvd{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", playbackTime=" + playbackTime +
                ", imageUrl='" + imageUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rating")
    @Expose
    private Integer rating;
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
    @SerializedName("url")
    @Expose
    private String url;

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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

    public void setUrl(String url) {
        this.url = url;
    }

}
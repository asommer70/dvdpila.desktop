package com.thehoick.dvdpila;

import java.util.Date;

public class Dvd {
    private int mId;
    private String mTitle;
    private int mRating;
    private String mAbstractTxt;
    private String mAbstractSource;
    private String mAbstractUrl;
    private String mFileUrl;
    private int mPlaybackTime;
    private Date mCreatedAt;
    private Date mUpdatedAt;
    private String mImageUrl;

    public Dvd(String title) {
        mTitle = title;
    }

    @Override
    public String toString() {
        return "Dvd{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mRating=" + mRating +
                ", mAbstractTxt='" + mAbstractTxt + '\'' +
                ", mAbstractSource='" + mAbstractSource + '\'' +
                ", mAbstractUrl='" + mAbstractUrl + '\'' +
                ", mFileUrl='" + mFileUrl + '\'' +
                ", mPlaybackTime=" + mPlaybackTime +
                ", mCreatedAt=" + mCreatedAt +
                ", mUpdatedAt=" + mUpdatedAt +
                ", mImageUrl='" + mImageUrl + '\'' +
                '}';
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        mRating = rating;
    }

    public String getAbstractTxt() {
        return mAbstractTxt;
    }

    public void setAbstractTxt(String abstractTxt) {
        mAbstractTxt = abstractTxt;
    }

    public String getAbstractSource() {
        return mAbstractSource;
    }

    public void setAbstractSource(String abstractSource) {
        mAbstractSource = abstractSource;
    }

    public String getAbstractUrl() {
        return mAbstractUrl;
    }

    public void setAbstractUrl(String abstractUrl) {
        mAbstractUrl = abstractUrl;
    }

    public String getFileUrl() {
        return mFileUrl;
    }

    public void setFileUrl(String fileUrl) {
        mFileUrl = fileUrl;
    }

    public int getPlaybackTime() {
        return mPlaybackTime;
    }

    public void setPlaybackTime(int playbackTime) {
        mPlaybackTime = playbackTime;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public Date getUpdatedAt() {
        return mUpdatedAt;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}

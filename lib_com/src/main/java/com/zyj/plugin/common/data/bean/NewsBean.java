package com.zyj.plugin.common.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsBean implements Parcelable {


    /**
     * id : 2
     * title : 沉醉在西藏的至美七月
     * publishTime : 2019-04-30 13:23:55
     * views : 1718
     * vote : 259
     * coverUrl : http://travel.enn.cn/group1/M00/00/1C/CiaAUlzITRGAZ7RjABJme0Qsvw8581.jpg
     * states : 1
     */

    private int id;
    private String title;
    private String publishTime;
    private int views;
    private int vote;
    private String coverUrl;
    private int states;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getViews() {
        return views;
    }

    public String getViewsStr() {
        return String.valueOf(views);
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getVote() {
        return vote;
    }

    public String getVoteStr() {
        return String.valueOf(vote);
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.publishTime);
        dest.writeInt(this.views);
        dest.writeInt(this.vote);
        dest.writeString(this.coverUrl);
        dest.writeInt(this.states);
    }

    public NewsBean() {
    }

    protected NewsBean(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.publishTime = in.readString();
        this.views = in.readInt();
        this.vote = in.readInt();
        this.coverUrl = in.readString();
        this.states = in.readInt();
    }

    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel source) {
            return new NewsBean(source);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };
}

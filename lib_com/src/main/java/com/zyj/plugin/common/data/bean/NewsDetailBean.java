package com.zyj.plugin.common.data.bean;

import java.util.ArrayList;
import java.util.List;

public class NewsDetailBean {


    /**
     * id : 2
     * title : 沉醉在西藏的至美七月
     * publishTime : 2019-04-30 13:23:55
     * views : 1718
     * vote : 259
     * content : [
     * {
     * "type": "content",
     * "value": "夏季的西藏，才是真正的西藏。雪越深，西藏才会越纯真。唯一要注意的就是，一定要选择一家靠谱的旅行社或者导游。其余的完全不用担心，我就是夏季去的西藏，真的太美了。比我之前2月份的时候去西藏美的太多了。"
     * },
     * {
     * "type": "content",
     * "value": "首先，夏季的西藏空气是最通透的时候，天空完全是纯粹的蓝色，没有任何云朵，蓝的像Ps出来的。在林芝的大峡谷，可以完美的看到最为壮观的南迦巴瓦峰。而在2、3、4月去的时候，是很难看到的，因为云朵太多了，南迦巴瓦峰一直藏在云朵里面。"
     * },
     * {
     * "type": "img",
     * "value": "http://travel.enn.cn/group1/M00/00/1C/CiaAUlzITRGAZ7RjABJme0Qsvw8581.jpg"
     * },
     * {
     * "type": "content",
     * "value": "夏季的南迦巴瓦峰，基本上每天的傍晚都能看到金色的祥云。图片上体验不到那种震撼，到了之后简直让人震惊。"
     * },
     * {
     * "type": "content",
     * "value": "夏季的西藏万里层云，千山暮雪，喇嘛红衣，海子傍雪山，村庄绕经幡。此时没有了夏季的雨雾朦胧，看到秀美雪峰的概率也会大大增加，此时的林芝不再只有江南水乡般的小家碧玉，而是多了几分磅礴大气苍茫肃穆，此时各种高原湖泊也不再水波荡漾，取而代之的是如天空般湛蓝的冰雪奇观。所以，夏季来西藏，真的是再合适不过了！"
     * },
     * {
     * "type": "img",
     * "value": "http://travel.enn.cn/group1/M00/00/1C/CiaAUlzIT8qAEpgdABUpcOijkJ0866.jpg"
     * }
     * ]
     * auther : 敏子
     * coverUrl : http://travel.enn.cn/group1/M00/00/1C/CiaAUlzITRGAZ7RjABJme0Qsvw8581.jpg
     * states : null
     */

    private int id;
    private String title;
    private String publishTime;
    private int views;
    private int vote;
    private String content;
    private String auther;
    private String coverUrl;
    private String states;
    private String description;
    private List<CommonContent> commonContents;

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

    public void setViews(int views) {
        this.views = views;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public List<CommonContent> getCommonContents() {
        if (commonContents == null) {
            return new ArrayList<>();
        }
        return commonContents;
    }

    public void setCommonContents(List<CommonContent> commonContents) {
        this.commonContents = commonContents;
    }
    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.zyj.plugin.common.data.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者 zhouchao
 * @日期 2019/5/7
 * @描述
 */
public class VenueDetailBean {

    private int id;
    private String placeName;
    private String account;
    private String picture;
    private String content;
    private String createTime;
    private List<CommonContent> commonContents;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
}

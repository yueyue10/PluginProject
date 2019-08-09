package com.zyj.plugin.common.data.bean;

/**
 * Created by zhaoyuejun on 2017/11/14.
 */

public class SettingBean {

    public int resId;
    public String title;
    public String versionCode;
    public int item_style;//1.普通分割线；2.较宽的分割线

    public SettingBean(String title) {
        this.title = title;
    }

    public SettingBean(String title, int resId) {
        this.item_style = 1;
        this.title = title;
        this.resId = resId;
    }

    public SettingBean(int item_style, String title) {
        this.title = title;
        this.item_style = item_style;
    }

    public SettingBean(String title, String versionCode) {
        this.title = title;
        this.versionCode = versionCode;
    }

    public SettingBean(String title, int resId, int item_style) {
        this.title = title;
        this.resId = resId;
        this.item_style = item_style;
    }
}

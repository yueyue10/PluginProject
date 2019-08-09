package com.zyj.plugin.common.data.bean;


import com.zyj.plugin.common.mvp.BaseActivity;

public class ComIntentBean {

    private String name;
    private Class<? extends BaseActivity> activityClass;

    public ComIntentBean(String name,Class<? extends BaseActivity> activityClass) {
        this.name = name;
        this.activityClass = activityClass;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends BaseActivity> getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class<? extends BaseActivity> activityClass) {
        this.activityClass = activityClass;
    }
}

package com.zyj.plugin.common.data.bean;

/**
 * Created by zhaoyuejun on 2018/11/26.
 */

public class CommonContent {

    private String type;//content;img
    private String value;

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value == null ? "" : value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

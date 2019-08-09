package com.zyj.plugin.common.data.bean;

public class KeyValueBean {

    private String key;
    private String value;
    public int item_style;//1.普通分割线；2.较宽的分割线

    public KeyValueBean(String key, String value, int item_style) {
        this.key = key;
        this.value = value;
        this.item_style = item_style;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value == null ? "" : value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getItem_style() {
        return item_style;
    }

    public void setItem_style(int item_style) {
        this.item_style = item_style;
    }
}

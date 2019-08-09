package com.zyj.plugin.common.data.bean;

public class EditValueBean {

    private String key;
    private String hint;
    private String value;
    private boolean required = true;//默认显示
    private int showRight = 0;//默认不显示

    public EditValueBean(String key, String hint) {
        this(key, hint, 0, true);
    }

    public EditValueBean(String key, String hint, int showRight) {
        this(key, hint, showRight, true);
    }

    public EditValueBean(String key, String hint, boolean required) {
        this(key, hint, 0, required);
    }

    public EditValueBean(String key, String hint, int showRight, boolean required) {
        this.key = key;
        this.hint = hint;
        this.required = required;
        this.showRight = showRight;
    }

    public EditValueBean(String key, String hint, String value) {
        this.key = key;
        this.hint = hint;
        this.value = value;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHint() {
        return hint == null ? "" : hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getValue() {
        return value == null ? "" : value;
    }

    public EditValueBean setValue(String value) {
        this.value = value;
        return this;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getShowRight() {
        return showRight;
    }
}

package com.zyj.plugin.common.data.bean;

public class ResourceBean {
    private String name;
    private String orderStatus;
    private int resId;
    private int style;//1:窄线条 2：宽线条

    //首页九宫格列表 我的界面列表-我的订单
    public ResourceBean(String name, int resId) {
        this.name = name;
        this.resId = resId;
        this.style = 1;
    }

    //我的界面列表
    public ResourceBean(String name, int resId, int style) {
        this.name = name;
        this.resId = resId;
        this.style = style;
    }

    //我的界面列表-我的订单
    public ResourceBean(String name, int resId, String orderStatus) {
        this.name = name;
        this.resId = resId;
        this.orderStatus = orderStatus;
        this.style = 1;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getStyle() {
        return style;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus == null ? "" : orderStatus;
    }
}

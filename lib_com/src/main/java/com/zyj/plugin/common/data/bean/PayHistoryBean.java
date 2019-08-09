package com.zyj.plugin.common.data.bean;

public class PayHistoryBean {


    /**
     * createTime : string
     * orderCode : string
     * parkPrice : 0
     * payType : string
     * plateNumber : string
     */

    private String createTime;
    private String orderCode;
    private double parkPrice;
    private String payType;
    private String plateNumber;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public double getParkPrice() {
        return parkPrice;
    }

    public void setParkPrice(double parkPrice) {
        this.parkPrice = parkPrice;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}

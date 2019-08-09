package com.zyj.plugin.common.data.bean;

public class NoPayOrderBean {

    /**
     * orderCode : 19071714380308
     * parkName : 梦廊坊地下停车场1
     * plateNumber : 京WDDWDF
     * startTime : 07:37
     * endTime : 10:37
     * totalPrice : 26
     * orderState : 0
     * createTime : 2019-07-17 14:38:03
     * carPictures : null
     * amountNoPay : 2
     */

    private String orderCode;
    private String parkName;
    private String plateNumber;
    private String startTime;
    private String endTime;
    private int totalPrice;
    private int orderState;
    private String createTime;
    private Object carPictures;
    private int amountNoPay;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getCarPictures() {
        return carPictures;
    }

    public void setCarPictures(Object carPictures) {
        this.carPictures = carPictures;
    }

    public int getAmountNoPay() {
        return amountNoPay;
    }

    public void setAmountNoPay(int amountNoPay) {
        this.amountNoPay = amountNoPay;
    }
}

package com.zyj.plugin.common.data.bean;

public class OrderBean {

    /**
     * carPictures : string
     * createTime : string
     * endTime : string
     * orderCode : string
     * orderState : 0
     * parkName : string
     * plateNumber : string
     * startTime : string
     * totalPrice : 0
     */

    private String carPictures;
    private String createTime;
    private String endTime;
    private String orderCode;
    private int orderState;// 0 待支付 1预约完成 2 已取消 3 订单关闭 4 已完成
    private String parkName;
    private String plateNumber;
    private String startTime;
    private double totalPrice;

    public String getCarPictures() {
        return carPictures;
    }

    public void setCarPictures(String carPictures) {
        this.carPictures = carPictures;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getOrderState() {
        return orderState;
    }

    public String getOrderStateStr() {
        //0 待支付 1预约完成 2 已取消 3 订单关闭 4 已完成
        String orderStates = "";
        switch (orderState) {
            case 0:
                orderStates = "待支付";
                break;
            case 1:
                orderStates = "预约完成";
                break;
            case 2:
                orderStates = "已取消";
                break;
            case 3:
                orderStates = "订单关闭";
                break;
            case 4:
                orderStates = "已完成";
                break;
        }
        return orderStates;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}

package com.zyj.plugin.common.data.bean;

/**
 * @作者 zhouchao
 * @日期 2019/7/12
 * @描述
 */
public class OnLinePayOrderBean {

    /**
     * endTime : 2019-07-12T10:41:41.527Z
     * orderCode : string
     * orderId : 0
     * parkName : string
     * parkPrice : 0.0
     * parkingTime : 0
     * plateNumber : string
     * startTime : 2019-07-12T10:41:41.527Z
     */

    private String endTime;
    private String orderCode;
    private int orderId;
    private String parkName;
    private double parkPrice;
    private int parkingTime;
    private String plateNumber;
    private String startTime;

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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public double getParkPrice() {
        return parkPrice;
    }

    public void setParkPrice(double parkPrice) {
        this.parkPrice = parkPrice;
    }

    public int getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
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
}

package com.zyj.plugin.common.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderDetailBean implements Parcelable {

    /**
     * carPictures : string
     * commissionPrice : 0
     * createTime : string
     * discountPrice : 0
     * endTime : 2019-07-11T02:37:10.840Z
     * isdelete : 0
     * memberId : 0
     * orderCode : string
     * orderState : 0
     * parkCode : string
     * parkName : string
     * parkPrice : 0
     * parkReservationOrderId : 0
     * plateNumber : string
     * reservationPrice : 0
     * startTime : 2019-07-11T02:37:10.840Z
     * total : 0
     * updateTime : 2019-07-11T02:37:10.840Z
     */

    private String carPictures;
    private double commissionPrice;
    private String createTime;
    private double discountPrice;
    private String endTime;
    private int isdelete;
    private int memberId;
    private String orderCode;
    private int orderState;
    private String parkCode;
    private String parkName;
    private double parkPrice;
    private int parkReservationOrderId;
    private String plateNumber;
    private double reservationPrice;
    private String startTime;
    private double total;
    private String updateTime;
    private double shouldPay;
    private double actualPay;

    public String getCarPictures() {
        return carPictures;
    }

    public void setCarPictures(String carPictures) {
        this.carPictures = carPictures;
    }

    public double getCommissionPrice() {
        return commissionPrice;
    }

    public void setCommissionPrice(double commissionPrice) {
        this.commissionPrice = commissionPrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public String getDiscountPriceStr() {
        return String.valueOf((int) discountPrice);
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
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

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
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

    public int getParkReservationOrderId() {
        return parkReservationOrderId;
    }

    public void setParkReservationOrderId(int parkReservationOrderId) {
        this.parkReservationOrderId = parkReservationOrderId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getReservationPrice() {
        return reservationPrice;
    }

    public String getReservationPriceStr() {
        return String.valueOf(reservationPrice);
    }

    public void setReservationPrice(double reservationPrice) {
        this.reservationPrice = reservationPrice;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public double getTotal() {
        return total;
    }

    public String getTotalStr() {
        return String.valueOf((int) total);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public double getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(double shouldPay) {
        this.shouldPay = shouldPay;
    }

    public double getActualPay() {
        return actualPay;
    }

    public void setActualPay(double actualPay) {
        this.actualPay = actualPay;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.carPictures);
        dest.writeDouble(this.commissionPrice);
        dest.writeString(this.createTime);
        dest.writeDouble(this.discountPrice);
        dest.writeString(this.endTime);
        dest.writeInt(this.isdelete);
        dest.writeInt(this.memberId);
        dest.writeString(this.orderCode);
        dest.writeInt(this.orderState);
        dest.writeString(this.parkCode);
        dest.writeString(this.parkName);
        dest.writeDouble(this.parkPrice);
        dest.writeInt(this.parkReservationOrderId);
        dest.writeString(this.plateNumber);
        dest.writeDouble(this.reservationPrice);
        dest.writeString(this.startTime);
        dest.writeDouble(this.total);
        dest.writeString(this.updateTime);
        dest.writeDouble(this.shouldPay);
        dest.writeDouble(this.actualPay);
    }

    public OrderDetailBean() {
    }

    protected OrderDetailBean(Parcel in) {
        this.carPictures = in.readString();
        this.commissionPrice = in.readDouble();
        this.createTime = in.readString();
        this.discountPrice = in.readDouble();
        this.endTime = in.readString();
        this.isdelete = in.readInt();
        this.memberId = in.readInt();
        this.orderCode = in.readString();
        this.orderState = in.readInt();
        this.parkCode = in.readString();
        this.parkName = in.readString();
        this.parkPrice = in.readDouble();
        this.parkReservationOrderId = in.readInt();
        this.plateNumber = in.readString();
        this.reservationPrice = in.readDouble();
        this.startTime = in.readString();
        this.total = in.readDouble();
        this.updateTime = in.readString();
        this.shouldPay = in.readDouble();
        this.actualPay = in.readDouble();
    }

    public static final Creator<OrderDetailBean> CREATOR = new Creator<OrderDetailBean>() {
        @Override
        public OrderDetailBean createFromParcel(Parcel source) {
            return new OrderDetailBean(source);
        }

        @Override
        public OrderDetailBean[] newArray(int size) {
            return new OrderDetailBean[size];
        }
    };
}

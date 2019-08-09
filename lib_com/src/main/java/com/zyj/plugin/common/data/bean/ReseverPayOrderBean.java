package com.zyj.plugin.common.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @作者 zhouchao
 * @日期 2019/7/15
 * @描述
 */
public class ReseverPayOrderBean implements Parcelable {

    /**
     * createTime : 2019-07-15T07:44:40.708Z
     * discountPrice : 0.0
     * endTime : 2019-07-15T07:44:40.708Z
     * orderCode : string
     * orderId : 0
     * parkName : string
     * plateNumber : string
     * shouldPay : 0.0
     * startTime : 2019-07-15T07:44:40.708Z
     * total : 0.0
     */

    private String createTime;
    private double discountPrice;
    private String endTime;
    private String orderCode;
    private int orderId;
    private String parkName;
    private String plateNumber;
    private double shouldPay;
    private String startTime;
    private double total;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getDiscountPrice() {
        return discountPrice;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public double getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(double shouldPay) {
        this.shouldPay = shouldPay;
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

    public void setTotal(double total) {
        this.total = total;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createTime);
        dest.writeDouble(this.discountPrice);
        dest.writeString(this.endTime);
        dest.writeString(this.orderCode);
        dest.writeInt(this.orderId);
        dest.writeString(this.parkName);
        dest.writeString(this.plateNumber);
        dest.writeDouble(this.shouldPay);
        dest.writeString(this.startTime);
        dest.writeDouble(this.total);
    }

    public ReseverPayOrderBean() {
    }

    protected ReseverPayOrderBean(Parcel in) {
        this.createTime = in.readString();
        this.discountPrice = in.readDouble();
        this.endTime = in.readString();
        this.orderCode = in.readString();
        this.orderId = in.readInt();
        this.parkName = in.readString();
        this.plateNumber = in.readString();
        this.shouldPay = in.readDouble();
        this.startTime = in.readString();
        this.total = in.readDouble();
    }

    public static final Creator<ReseverPayOrderBean> CREATOR = new Creator<ReseverPayOrderBean>() {
        @Override
        public ReseverPayOrderBean createFromParcel(Parcel source) {
            return new ReseverPayOrderBean(source);
        }

        @Override
        public ReseverPayOrderBean[] newArray(int size) {
            return new ReseverPayOrderBean[size];
        }
    };
}

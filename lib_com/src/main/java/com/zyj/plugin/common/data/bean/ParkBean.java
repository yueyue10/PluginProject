package com.zyj.plugin.common.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @作者 zhouchao
 * @日期 2019/7/10
 * @描述
 */
public class ParkBean implements Parcelable {

    /**
     * address : string
     * descs : string
     * eptCarports : 0
     * freeTime : 0
     * id : 0
     * latitude : string
     * longitude : string
     * parkName : string
     * priceDesc : string
     * state : 0
     * parkType : 1
     * totalCarports : 0
     * passFlexibleTime:0
     */

    private String address;
    private String descs;
    private int eptCarports;
    private int freeTime;
    private int id;
    private String latitude;
    private String longitude;
    private String parkName;
    private String priceDesc;
    private int state;//0 禁用 1正常
    private int totalCarports;
    private int parkType;//1地上 2地下
    private int passFlexibleTime;//缴费后免费时长 单位分钟

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public int getEptCarports() {
        return eptCarports;
    }

    public void setEptCarports(int eptCarports) {
        this.eptCarports = eptCarports;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getPriceDesc() {
        return priceDesc;
    }

    public void setPriceDesc(String priceDesc) {
        this.priceDesc = priceDesc;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTotalCarports() {
        return totalCarports;
    }

    public void setTotalCarports(int totalCarports) {
        this.totalCarports = totalCarports;
    }

    public int getParkType() {
        return parkType;
    }

    public void setParkType(int parkType) {
        this.parkType = parkType;
    }

    public int getPassFlexibleTime() {
        return passFlexibleTime;
    }

    public void setPassFlexibleTime(int passFlexibleTime) {
        this.passFlexibleTime = passFlexibleTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.descs);
        dest.writeInt(this.eptCarports);
        dest.writeInt(this.freeTime);
        dest.writeInt(this.id);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.parkName);
        dest.writeString(this.priceDesc);
        dest.writeInt(this.state);
        dest.writeInt(this.totalCarports);
        dest.writeInt(this.parkType);
        dest.writeInt(this.passFlexibleTime);
    }

    public ParkBean() {
    }

    protected ParkBean(Parcel in) {
        this.address = in.readString();
        this.descs = in.readString();
        this.eptCarports = in.readInt();
        this.freeTime = in.readInt();
        this.id = in.readInt();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.parkName = in.readString();
        this.priceDesc = in.readString();
        this.state = in.readInt();
        this.totalCarports = in.readInt();
        this.parkType = in.readInt();
        this.passFlexibleTime = in.readInt();
    }

    public static final Creator<ParkBean> CREATOR = new Creator<ParkBean>() {
        @Override
        public ParkBean createFromParcel(Parcel source) {
            return new ParkBean(source);
        }

        @Override
        public ParkBean[] newArray(int size) {
            return new ParkBean[size];
        }
    };
}

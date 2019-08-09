package com.zyj.plugin.common.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @作者 zhouchao
 * @日期 2019/7/11
 * @描述
 */
public class ParkingInfoBean implements Parcelable {

    /**
     * carCode : string
     * carImage : string
     * carportNO : string
     * enterTime : string
     * floorNO : string
     * locateImage : string
     */

    private String carCode;
    private String carImage;
    private String carportNO;
    private String enterTime;
    private String floorNO;
    private String locateImage;
    private String parkName;

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getCarportNO() {
        return carportNO;
    }

    public void setCarportNO(String carportNO) {
        this.carportNO = carportNO;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getFloorNO() {
        return floorNO;
    }

    public void setFloorNO(String floorNO) {
        this.floorNO = floorNO;
    }

    public String getLocateImage() {
        return locateImage;
    }

    public void setLocateImage(String locateImage) {
        this.locateImage = locateImage;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.carCode);
        dest.writeString(this.carImage);
        dest.writeString(this.carportNO);
        dest.writeString(this.enterTime);
        dest.writeString(this.floorNO);
        dest.writeString(this.locateImage);
        dest.writeString(this.parkName);
    }

    public ParkingInfoBean() {
    }

    protected ParkingInfoBean(Parcel in) {
        this.carCode = in.readString();
        this.carImage = in.readString();
        this.carportNO = in.readString();
        this.enterTime = in.readString();
        this.floorNO = in.readString();
        this.locateImage = in.readString();
        this.parkName = in.readString();
    }

    public static final Creator<ParkingInfoBean> CREATOR = new Creator<ParkingInfoBean>() {
        @Override
        public ParkingInfoBean createFromParcel(Parcel source) {
            return new ParkingInfoBean(source);
        }

        @Override
        public ParkingInfoBean[] newArray(int size) {
            return new ParkingInfoBean[size];
        }
    };
}

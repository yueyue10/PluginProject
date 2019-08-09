package com.zyj.plugin.common.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PassengerInfoBean implements Parcelable {

    /**
     * id : 1
     * name : 王五
     * idCard : 110324198710012345
     */

    private int id;
    private String name;
    private String idCard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.idCard);
    }

    public PassengerInfoBean() {
    }

    protected PassengerInfoBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.idCard = in.readString();
    }

    public static final Creator<PassengerInfoBean> CREATOR = new Creator<PassengerInfoBean>() {
        @Override
        public PassengerInfoBean createFromParcel(Parcel source) {
            return new PassengerInfoBean(source);
        }

        @Override
        public PassengerInfoBean[] newArray(int size) {
            return new PassengerInfoBean[size];
        }
    };
}

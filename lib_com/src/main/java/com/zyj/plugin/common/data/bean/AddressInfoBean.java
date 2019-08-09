package com.zyj.plugin.common.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class AddressInfoBean implements Parcelable {

    /**
     * companyId : 0
     * createTime : 2019-07-12T06:22:16.009Z
     * detailedAddress : string
     * id : 0
     * memberId : 0
     * name : string
     * postCode : 0
     * telephone : string
     * updateTime : 2019-07-12T06:22:16.009Z
     */

    private int companyId;
    private String createTime;
    private String area;
    private String detailedAddress;
    private int id;
    private int memberId;
    private String name;
    private int postCode;
    private String telephone;
    private String updateTime;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getArea() {
        return area == null ? "" : area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getPostCodeStr() {
        return String.valueOf(postCode);
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.companyId);
        dest.writeString(this.createTime);
        dest.writeString(this.area);
        dest.writeString(this.detailedAddress);
        dest.writeInt(this.id);
        dest.writeInt(this.memberId);
        dest.writeString(this.name);
        dest.writeInt(this.postCode);
        dest.writeString(this.telephone);
        dest.writeString(this.updateTime);
    }

    public AddressInfoBean() {
    }

    protected AddressInfoBean(Parcel in) {
        this.companyId = in.readInt();
        this.createTime = in.readString();
        this.area = in.readString();
        this.detailedAddress = in.readString();
        this.id = in.readInt();
        this.memberId = in.readInt();
        this.name = in.readString();
        this.postCode = in.readInt();
        this.telephone = in.readString();
        this.updateTime = in.readString();
    }

    public static final Creator<AddressInfoBean> CREATOR = new Creator<AddressInfoBean>() {
        @Override
        public AddressInfoBean createFromParcel(Parcel source) {
            return new AddressInfoBean(source);
        }

        @Override
        public AddressInfoBean[] newArray(int size) {
            return new AddressInfoBean[size];
        }
    };
}

package com.zyj.plugin.common.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @作者 zhouchao
 * @日期 2019/5/6
 * @描述
 */
public class VenueBean implements Parcelable {

    /**
     * id : 24
     * placeName : 五间墅营地
     * account : 五间墅营地为室外开放式营地，配套自费项目：帐篷、防潮垫等；可接室外草坪婚礼、团队活动、拓展运动、会议等，最大接待量为300人。
     * picture : http://travel.enn.cn/group1/M00/00/52/CiaAUlwHQjuARyK9AAuicmpHWT0032.jpg
     * remark : <p><img src="http://travel.enn.cn/encdata-venus/img/reachText/20181228/1545975861651045022.jpg" title="1545975861651045022.jpg" alt="微信图片_20181127135022.jpg"/><img src="http://travel.enn.cn/encdata-venus/img/reachText/20181228/1545975867019029471.jpg" title="1545975867019029471.jpg" alt="微信图片_201809111406028.jpg"/><img src="http://travel.enn.cn/encdata-venus/img/reachText/20181228/1545975875897033150.jpg" title="1545975875897033150.jpg" alt="微信图片_20180911135718.jpg"/></p><p><br/></p>
     * createTime : 2018-12-05 11:13:02.0
     */

    private int id;
    private String placeName;
    private String account;
    private String picture;
    private String remark;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.placeName);
        dest.writeString(this.account);
        dest.writeString(this.picture);
        dest.writeString(this.remark);
        dest.writeString(this.createTime);
    }

    public VenueBean() {
    }

    protected VenueBean(Parcel in) {
        this.id = in.readInt();
        this.placeName = in.readString();
        this.account = in.readString();
        this.picture = in.readString();
        this.remark = in.readString();
        this.createTime = in.readString();
    }

    public static final Creator<VenueBean> CREATOR = new Creator<VenueBean>() {
        @Override
        public VenueBean createFromParcel(Parcel source) {
            return new VenueBean(source);
        }

        @Override
        public VenueBean[] newArray(int size) {
            return new VenueBean[size];
        }
    };
}

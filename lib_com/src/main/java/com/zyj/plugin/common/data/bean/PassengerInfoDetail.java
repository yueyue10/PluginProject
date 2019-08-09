package com.zyj.plugin.common.data.bean;

public class PassengerInfoDetail {

    /**
     * birthday : string
     * companyId : 0
     * createTime : 2019-07-12T06:22:15.858Z
     * id : 0
     * idCard : string
     * memberId : 0
     * name : string
     * sex : 0
     * updateTime : 2019-07-12T06:22:15.858Z
     */

    private String birthday;
    private int companyId;
    private String createTime;
    private int id;
    private String idCard;
    private int memberId;
    private String name;
    private int sex;//旅客性别1.男 2.女
    private String updateTime;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public int getSex() {
        return sex;
    }

    public String getSexStr() {
        String sexStr = "男";
        switch (sex) {//旅客性别1.男 2.女
            case 1:
                sexStr = "男";
                break;
            case 2:
                sexStr = "女";
                break;
        }
        return sexStr;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}

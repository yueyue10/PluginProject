package com.zyj.plugin.common.data.bean;

public class WxPayUnifiedOrderResultBean {

    /**
     * sign : 6870CE55A1AF6F42D344F5A61699A24E
     * prepayId : wx22102327753026581cddd5991715053000
     * partnerId : 1511663861
     * appId : wx0b486678c26d9707
     * packageValue : Sign=WXPay
     * timeStamp : 1563762207
     * nonceStr : 1563762207780
     */

    private String sign;
    private String prepayId;
    private String partnerId;
    private String appId;
    private String packageValue;
    private String timeStamp;
    private String nonceStr;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
}
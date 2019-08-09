package com.zyj.plugin.common.data.bean;

public class WxPayResult {
    private String result;
    private String orderCode;
    private boolean isPaySuccess;

    public String getResult() {
        return result == null ? "" : result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOrderCode() {
        return orderCode == null ? "" : orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public boolean isPaySuccess() {
        return isPaySuccess;
    }

    public void setPaySuccess(boolean paySuccess) {
        isPaySuccess = paySuccess;
    }
}

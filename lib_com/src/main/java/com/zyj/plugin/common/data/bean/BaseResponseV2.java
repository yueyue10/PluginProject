package com.zyj.plugin.common.data.bean;

/**
 * @作者 zhouchao
 * @日期 2019/7/10
 * @描述
 */
public class BaseResponseV2<T> {
    public static final int SUCCESS = 1;
    public static final int FAIL = 2;

    /**
     * 1:成功 2:请求参数非法 3:业务异常 -1:登录超时 -2:系统内部异常 ,
     */
    private int result;

    private String message;

    private T value;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

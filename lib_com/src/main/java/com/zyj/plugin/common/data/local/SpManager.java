package com.zyj.plugin.common.data.local;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.zyj.plugin.common.BaseApplication;
import com.zyj.plugin.common.data.bean.OrderDetailBean;
import com.zyj.plugin.common.data.bean.UserInfo;
import com.zyj.plugin.common.data.bean.WxPayUnifiedOrderResultBean;

import static android.content.Context.MODE_PRIVATE;

/**
 * @作者 zhouchao
 * @日期 2019/3/28
 * @描述
 */
public class SpManager {

    private static SpManager spManager;
    private SharedPreferences spUser;

    private SpManager() {
        spUser = BaseApplication.getInstance().getSharedPreferences("user", MODE_PRIVATE);
    }

    //2017年3月14日 17:09:32 修改为DCL 单例模式
    public static SpManager getInstance() {
        if (spManager == null) {
            synchronized (SpManager.class) {
                if (spManager == null)
                    spManager = new SpManager();
            }
        }
        return spManager;
    }

    public UserInfo getUserUserInfo() {
        UserInfo userInfo = GsonUtils.fromJson(spUser.getString("userInfo", ""), UserInfo.class);
        if (userInfo != null)
            return userInfo;
        return new UserInfo();
    }

    public OrderDetailBean getOrderDetail() {
        OrderDetailBean orderDetailBean = GsonUtils.fromJson(spUser.getString("orderDetail", ""), OrderDetailBean.class);
        if (orderDetailBean != null)
            return orderDetailBean;
        return new OrderDetailBean();
    }

    public WxPayUnifiedOrderResultBean getWxPayOrder() {
        WxPayUnifiedOrderResultBean wxPayOrder = GsonUtils.fromJson(spUser.getString("wxPayOrder", ""), WxPayUnifiedOrderResultBean.class);
        if (wxPayOrder != null)
            return wxPayOrder;
        return new WxPayUnifiedOrderResultBean();
    }

    public void putUserUserInfo(String userInfo) {
        spUser.edit().putString("userInfo", userInfo).apply();
    }

    public void putOrderDetail(OrderDetailBean orderDetailBean) {
        spUser.edit().putString("orderDetail", GsonUtils.toJson(orderDetailBean)).apply();
    }

    public void putWxPayOrder(WxPayUnifiedOrderResultBean wxPayOrder) {
        spUser.edit().putString("wxPayOrder", GsonUtils.toJson(wxPayOrder)).apply();
    }

    public String getToken() {
        return spUser.getString("token", "");
    }

    public void putToken(String token) {
        spUser.edit().putString("token", token).apply();
    }

    public int getUserId() {
        return spUser.getInt("userId", -1);
    }

    public void putUserId(int id) {
        spUser.edit().putInt("userId", id).apply();
    }

    public void putNickName(String nickName) {
        spUser.edit().putString("nickName", nickName).apply();
    }

    public String getNickName() {
        return spUser.getString("getNickName", "");
    }

    public void putHeadImg(String headImg) {
        spUser.edit().putString("headImg", headImg).apply();
    }

    public String getHeadImg() {
        return spUser.getString("headImg", "");
    }

    public void putBirthday(String birthday) {
        spUser.edit().putString("birthday", birthday).apply();
    }

    public String getBirthday() {
        return spUser.getString("birthday", "");
    }

    public void clearAllUserData() {
        spUser.edit().clear().apply();
    }

    public void putUserInfo(UserInfo userInfo) {
        if (!TextUtils.isEmpty(userInfo.getToken()))
            putToken(userInfo.getToken());
        if (!TextUtils.isEmpty(userInfo.getNickName()))
            putNickName(userInfo.getNickName());
        if (!TextUtils.isEmpty(userInfo.getHeadImg()))
            putHeadImg(userInfo.getHeadImg());
        if (!TextUtils.isEmpty(userInfo.getBirthday()))
            putBirthday(userInfo.getBirthday());
        if (userInfo.getId() != 0) {
            putUserId(userInfo.getId());
        }
    }
}

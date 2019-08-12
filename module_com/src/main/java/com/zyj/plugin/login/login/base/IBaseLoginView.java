package com.zyj.plugin.login.login.base;

import com.zyj.plugin.common.mvp.view.AbstractView;

/**
 * @作者 zhouchao
 * @日期 2019/5/9
 * @描述
 */
public interface IBaseLoginView extends AbstractView {
    /**
     * 显示登录按钮，切换登录按钮状态
     *
     * @param clickable true 可点击状态 false不可点击状态
     */
    void changeButtonClickStatus(boolean clickable);

    /**
     * 改变获取验证码可点击状态
     *
     * @param clickable true 可点击状态 false不可点击状态
     */
    void changeimeCodeStatus(boolean clickable);

    /**
     * 显示密码是否可见
     *
     * @param visible true显示可见 false 不可见
     */
    void showPasswordVisible(boolean visible);

    /**
     * 获取验证码成功提示
     *
     * @param resultMessage
     */
    void showGetCodeSuccess(String resultMessage);


    /**
     * 展示获取验证码失败提示
     */
    void showGetCodeError();


    /**
     * 展示获取验证码失败提示
     */
    void showGetCodeError(String errorMessage);

    /**
     * 展示未输入手机号码提示
     */
    void showPhoneEmptyError();

    /**
     * 展示电话号码不足11位提示
     */
    void showPhoneNumberError();

    /**
     * 提示电话号码格式不正确
     */
    void showPhoneFormatError();
}

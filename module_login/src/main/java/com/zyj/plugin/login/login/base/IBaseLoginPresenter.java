package com.zyj.plugin.login.login.base;


import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.AbstractView;

/**
 * @作者 zhouchao
 * @日期 2019/5/9
 * @描述
 */
public interface IBaseLoginPresenter<T extends AbstractView> extends AbstractPresenter<T> {
    /**
     * 校验电话号码
     *
     * @param phoneNumber 电话号码
     */
    boolean verificationPhone(String phoneNumber);

    /**
     * 获取验证码
     *
     * @param phoneNumber 手机号码
     */
    void getLoginCode(String phoneNumber);

    /**
     * 更改是否需要显示密码
     */
    void changePasswordVisible();
}

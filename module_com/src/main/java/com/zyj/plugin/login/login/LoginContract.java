package com.zyj.plugin.login.login;

import com.zyj.plugin.login.login.base.IBaseLoginPresenter;
import com.zyj.plugin.login.login.base.IBaseLoginView;

/**
 * 登录Contract 登陆界面及逻辑
 *
 * @作者 zhouchao
 * @日期 2019/3/27
 * @描述
 */
public interface LoginContract {

    interface View extends IBaseLoginView {
        /**
         * 显示验证码登录界面
         */
        void showCodeLoginView();

        /**
         * 显示密码登录界面
         */
        void showPasswordLoginView();

        /**
         * 显示登录成功
         */
        void loginSuccess();

    }

    interface Presenter extends IBaseLoginPresenter<View> {

        /**
         * 登录
         *
         * @param phoneNumber    手机号码
         * @param passwordOrCode 验证码或者密码
         */
        void login(String phoneNumber, String passwordOrCode);

        /**
         * 校验手机号码和验证码或者密码，判断是否可以登录，是否可以发送验证码
         *
         * @param phoneNumber   手机号码 满足13位
         * @param code          验证码满足6位 密码的话不为空
         * @param isTimeCodeRun true 验证码倒计时中，false 验证码不在倒计时中
         */
        void verificationPhoneAndCode(String phoneNumber, String code, boolean isTimeCodeRun);

        /**
         * 检查登录是密码登录还是验证码登录
         */
        void checkLoginType();
    }
}

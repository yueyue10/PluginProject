package com.zyj.plugin.login.login.register;

import com.zyj.plugin.login.login.base.IBaseLoginPresenter;
import com.zyj.plugin.login.login.base.IBaseLoginView;

public interface RegisterContract {

    interface View extends IBaseLoginView {

        /**
         * 注册成功
         */
        void registerSuccess();

    }

    interface Presenter extends IBaseLoginPresenter<View> {
        /**
         * 注册
         *
         * @param phoneNumber
         * @param password
         * @param code
         */
        void register(String phoneNumber, String password, String code);


        /**
         * 校验手机号码和验证码或者密码，判断是否可以注册，是否可以发送验证码
         *
         * @param phoneNumber   手机号码 满足11位
         * @param code          验证码满足6位
         * @param password      密码需要不为空（有没有最低多少位待定）
         * @param isTimeCodeRun true 验证码倒计时中，false 验证码不在倒计时中
         */
        void verificationRegisterable(String phoneNumber, String password, String code, boolean isTimeCodeRun);

        /**
         * 更改agreement选中状态
         * 根据选中状态，改变注册按钮是否可以点击
         *
         * @param isChecked   是否选中
         * @param phoneNumber 手机号码
         * @param password    密码
         * @param code        验证码
         */
        void changeAgreementChecked(boolean isChecked, String phoneNumber, String password, String code);

    }
}
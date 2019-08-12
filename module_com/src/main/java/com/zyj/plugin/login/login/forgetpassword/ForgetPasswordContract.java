package com.zyj.plugin.login.login.forgetpassword;

import com.zyj.plugin.login.login.base.IBaseLoginPresenter;
import com.zyj.plugin.login.login.base.IBaseLoginView;

public interface ForgetPasswordContract {
    interface View extends IBaseLoginView {

        /**
         * 密码重置成功
         */
        void resetSuccess();

    }

    interface Presenter extends IBaseLoginPresenter<View> {

        /**
         * 重置密码
         *
         * @param phoneNumber 手机号码
         * @param password    密码
         * @param code        验证码
         */
        void resetPassword(String phoneNumber, String password, String code);

        /**
         * 校验手机号码和验证码或者密码，判断是否可以注册，是否可以发送验证码
         *
         * @param phoneNumber   手机号码 满足11位
         * @param code          验证码满足6位
         * @param password      密码需要不为空（有没有最低多少位待定）
         * @param isTimeCodeRun true 验证码倒计时中，false 验证码不在倒计时中
         */
        void verificationRegisterable(String phoneNumber, String password, String code, boolean isTimeCodeRun);
    }
}
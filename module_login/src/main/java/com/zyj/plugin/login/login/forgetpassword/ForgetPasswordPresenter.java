package com.zyj.plugin.login.login.forgetpassword;

import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.UserInfo;
import com.zyj.plugin.common.data.local.SpManager;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.login.login.base.BaseLoginPresenter;

import javax.inject.Inject;

/**
 * Presenter
 */
public class ForgetPasswordPresenter extends BaseLoginPresenter<ForgetPasswordContract.View> implements ForgetPasswordContract.Presenter {

    @Inject
    ForgetPasswordPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void resetPassword(String phoneNumber, String password, String code) {
        mView.showLoading();
        addSubscribeV2(dataManager.resetPassword(phoneNumber, password,password, code), new BaseObserver<UserInfo>(mView) {
            @Override
            public void onNext(UserInfo userInfo) {
                SpManager.getInstance().putUserUserInfo(GsonUtils.toJson(userInfo));
                SpManager.getInstance().putUserInfo(userInfo);
                mView.resetSuccess();
                mView.hideLoading();
            }
        });
    }


    @Override
    public void verificationRegisterable(String phoneNumber, String password, String code,boolean isTimeCodeRun) {
        if (!TextUtils.isEmpty(phoneNumber) && phoneNumber.length() == 11) {
            if (!isTimeCodeRun)
                mView.changeimeCodeStatus(true);
        } else {
            mView.changeimeCodeStatus(false);
        }

        if (!TextUtils.isEmpty(phoneNumber) && phoneNumber.length() == 11 && !TextUtils.isEmpty(password) && password.length() >= 6 && !TextUtils.isEmpty(code) && code.length() == 6) {
            mView.changeButtonClickStatus(true);
        } else {
            mView.changeButtonClickStatus(false);
        }
    }

}
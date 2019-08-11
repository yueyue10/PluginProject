package com.zyj.plugin.login.login.register;

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
public class RegisterPresenter extends BaseLoginPresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private boolean isAgreementChecked = false;

    @Inject
    RegisterPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void register(String phoneNumber, String password, String code) {
        mView.showLoading();
        addSubscribeV2(dataManager.register(phoneNumber, password, code), new BaseObserver<UserInfo>(mView) {
            @Override
            public void onNext(UserInfo userInfo) {
                SpManager.getInstance().putUserUserInfo(GsonUtils.toJson(userInfo));
                SpManager.getInstance().putUserInfo(userInfo);
                mView.registerSuccess();
                mView.hideLoading();
            }
        });
    }

    @Override
    public void verificationRegisterable(String phoneNumber, String password, String code, boolean isTimeCodeRun) {
        if (!TextUtils.isEmpty(phoneNumber) && phoneNumber.length() == 11) {
            if (!isTimeCodeRun)
                mView.changeimeCodeStatus(true);
        } else {
            mView.changeimeCodeStatus(false);
        }

        if (!TextUtils.isEmpty(phoneNumber) && phoneNumber.length() == 11 && !TextUtils.isEmpty(password) && password.length() >= 6 && !TextUtils.isEmpty(code) && code.length() == 6 && isAgreementChecked) {
            mView.changeButtonClickStatus(true);
        } else {
            mView.changeButtonClickStatus(false);
        }
    }

    @Override
    public void changeAgreementChecked(boolean isChecked, String phoneNumber, String password, String code) {
        isAgreementChecked = isChecked;
        if (isAgreementChecked && !TextUtils.isEmpty(phoneNumber) && phoneNumber.length() == 11 && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(code) && code.length() == 6) {
            mView.changeButtonClickStatus(true);
        } else {
            mView.changeButtonClickStatus(false);
        }
    }
}
package com.zyj.plugin.login.login.base;

import android.text.TextUtils;

import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.BaseResponseV2;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.common.data.utils.RxUtils;
import com.zyj.plugin.common.mvp.presenter.BasePresenter;
import com.zyj.plugin.common.uitl.CommonUtils;

/**
 * @作者 zhouchao
 * @日期 2019/5/9
 * @描述
 */
public class BaseLoginPresenter<T extends IBaseLoginView> extends BasePresenter<T> implements IBaseLoginPresenter<T> {

    public DataManager dataManager;
    public boolean isPassordVisible = false;

    public BaseLoginPresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public boolean verificationPhone(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            mView.showPhoneEmptyError();
            return false;
        } else if (phoneNumber.length() != 11) {
            mView.showPhoneNumberError();
            return false;
        } else if (!CommonUtils.verificationPhoneNumber(phoneNumber)) {
            mView.showPhoneFormatError();
            return false;
        }
        return true;
    }

    @Override
    public void getLoginCode(String phoneNumber) {
        if (verificationPhone(phoneNumber)) {
            mView.showLoading();
            addSubscribe(dataManager.getValidateCode(phoneNumber)
                    .compose(RxUtils.rxSchedulerHelper())
                    .subscribeWith(new BaseObserver<BaseResponseV2>(mView) {
                        @Override
                        public void onNext(BaseResponseV2 response) {
                            if (response != null && response.getResult() == BaseResponseV2.SUCCESS) {
                                mView.showGetCodeSuccess(response.getMessage());
                            } else if (response != null && TextUtils.isEmpty(response.getMessage())) {
                                mView.showGetCodeError(response.getMessage());
                            } else {
                                mView.showGetCodeError();
                            }
                            mView.hideLoading();
                        }
                    }));
        }
    }

    @Override
    public void changePasswordVisible() {
        if (isPassordVisible) {
            isPassordVisible = false;
        } else {
            isPassordVisible = true;
        }
        mView.showPasswordVisible(isPassordVisible);
    }
}

package com.zyj.plugin.login.login;

import android.text.TextUtils;

import com.blankj.utilcode.util.GsonUtils;
import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.BaseResponseV2;
import com.zyj.plugin.common.data.bean.UserInfo;
import com.zyj.plugin.common.data.local.SpManager;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.login.login.base.BaseLoginPresenter;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * @作者 zhouchao
 * @日期 2019/3/27
 * @描述
 */
public class LoginPresenter extends BaseLoginPresenter<LoginContract.View> implements LoginContract.Presenter {
    private boolean isCodeLogin = true;

    @Inject
    LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void login(String phoneNumber, String passwordOrCode) {
        Observable.just(phoneNumber)
                .filter(this::verificationPhone)
                .doOnNext(s -> mView.showLoading())
                .map(phoneNumber1 -> checkLoginType(passwordOrCode, phoneNumber1))
                .subscribe(new BaseObserver<Observable<BaseResponseV2<UserInfo>>>(mView) {
                    @Override
                    public void onNext(Observable<BaseResponseV2<UserInfo>> observable) {
                        loginByObservable(observable);
                    }
                });
    }

    private void loginByObservable(Observable<BaseResponseV2<UserInfo>> observable) {
        addSubscribeV2(observable, new BaseObserver<UserInfo>(mView) {
            @Override
            public void onNext(UserInfo userInfo) {
                SpManager.getInstance().putUserUserInfo(GsonUtils.toJson(userInfo));
                SpManager.getInstance().putUserInfo(userInfo);
                mView.loginSuccess();
                mView.hideLoading();
            }
        });
    }

    private Observable<BaseResponseV2<UserInfo>> checkLoginType(String passwordOrCode, String phoneNumber1) {
        if (isCodeLogin) {
            return dataManager.loginByCode(phoneNumber1, passwordOrCode, "2");
        }
        return dataManager.loginByPassword(phoneNumber1, passwordOrCode);
    }


    @Override
    public void verificationPhoneAndCode(String phoneNumber, String code, boolean isTimeCodeRun) {
        if (isCodeLogin && !TextUtils.isEmpty(phoneNumber) && phoneNumber.length() == 11) {
            if (!isTimeCodeRun)//倒计时中
                mView.changeimeCodeStatus(true);
            if (!TextUtils.isEmpty(code) && code.length() == 6) {
                mView.changeButtonClickStatus(true);
            } else {
                mView.changeButtonClickStatus(false);
            }
        } else if (isCodeLogin && TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            mView.changeimeCodeStatus(false);
            mView.changeButtonClickStatus(false);
        } else if (!isCodeLogin && !TextUtils.isEmpty(phoneNumber) && phoneNumber.length() == 11 && !TextUtils.isEmpty(code) && code.length() >= 6) {
            mView.changeButtonClickStatus(true);
        } else if (!isCodeLogin && TextUtils.isEmpty(phoneNumber) || phoneNumber.length() != 11 || TextUtils.isEmpty(code) || code.length() < 6) {
            mView.changeButtonClickStatus(false);
        }
    }

    @Override
    public void checkLoginType() {
        if (isCodeLogin) {
            mView.showPasswordLoginView();
            isCodeLogin = false;
        } else {
            mView.showCodeLoginView();
            isCodeLogin = true;
        }
    }
}

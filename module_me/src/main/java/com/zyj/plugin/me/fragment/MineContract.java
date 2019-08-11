package com.zyj.plugin.me.fragment;


import com.zyj.plugin.common.data.bean.UserInfo;
import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.BaseView;

/**
 * @author quchao
 * @date 2017/11/28
 */

public interface MineContract {

    interface View extends BaseView {
        void getUserInfoSuccess(UserInfo userInfo);

        void getNoPayInfoSuccess(int noPayNum);
    }

    interface Presenter extends AbstractPresenter<View> {
        void getUserInfo();

        void getNoPayInfo();
    }

}

package com.zyj.plugin.home.main;

import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.BaseView;

public interface MainContract {
    interface View extends BaseView {

    }

    interface Presenter extends AbstractPresenter<View> {
        void getUserInfo();
    }
}
package com.zyj.plugin.me.main;

import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.AbstractView;

public interface MainContract {
    interface View extends AbstractView {

    }

    interface Presenter extends AbstractPresenter<View> {
        void getUserInfo();
    }
}
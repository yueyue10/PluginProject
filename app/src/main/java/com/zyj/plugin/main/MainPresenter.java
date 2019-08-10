package com.zyj.plugin.main;

import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.mvp.presenter.BasePresenter;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getUserInfo() {

    }
}

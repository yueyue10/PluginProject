package com.zyj.plugin.home.fragment;

import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.common.data.utils.RxUtils;
import com.zyj.plugin.common.mvp.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by zhaoyuejun on 2018/11/14.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private DataManager mDataManager;

    @Inject
    HomePresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getHomeData(int scenicId) {
        addSubscribe(mDataManager.getHomeData(scenicId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<HomeBean>(mView) {
                    @Override
                    public void onNext(HomeBean homeAdBean) {
                        mView.getHomeDataSuccess(homeAdBean);
                    }
                }));
    }

}


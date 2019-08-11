package com.zyj.plugin.home.news;

import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.common.data.utils.RxUtils;
import com.zyj.plugin.common.mvp.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Presenter
 */
public class NewsListPresenter extends BasePresenter<NewsListContract.View> implements NewsListContract.Presenter {

    private DataManager dataManager;

    @Inject
    NewsListPresenter(DataManager dataManager) {
        super(dataManager);
        this.dataManager = dataManager;
    }

    @Override
    public void getNewsList(int scenicId) {
        addSubscribe(dataManager.getNewsList(scenicId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<List<NewsBean>>(mView) {
                    @Override
                    public void onNext(List<NewsBean> newsBeans) {
                        mView.hideLoading();
                        mView.getNewsListSuccess(newsBeans);
                    }
                }));
    }

    @Override
    public void getHomeData(int scenicId) {
        addSubscribe(dataManager.getHomeData(scenicId)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<HomeBean>(mView) {
                    @Override
                    public void onNext(HomeBean homeAdBean) {
                        mView.hideLoading();
                        mView.getHomeDataSuccess(homeAdBean);
                    }
                }));
    }

    @Override
    public void strategyInfoVote(int id, int position) {
        addSubscribe(dataManager.strategyInfoVote(id)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(new BaseObserver<Boolean>(mView) {
                    @Override
                    public void onNext(Boolean voteResult) {
                        mView.voteStrategySuccess(position);
                    }
                }));
    }
}
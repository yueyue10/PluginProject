package com.zyj.plugin.home.news;

import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.AbstractView;

import java.util.List;

public interface NewsListContract {

    interface View extends AbstractView {
        void voteStrategySuccess(int position);

        void getHomeDataSuccess(HomeBean homeAdBean);

        void getNewsListSuccess(List<NewsBean> newsBeans);
    }

    interface Presenter extends AbstractPresenter<View> {

        void getNewsList(int scenicId);

        void strategyInfoVote(int id, int position);

        void getHomeData(int scenicId);
    }
}
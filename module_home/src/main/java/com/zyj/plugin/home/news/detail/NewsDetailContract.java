package com.zyj.plugin.home.news.detail;

import com.zyj.plugin.common.data.bean.NewsDetailBean;
import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.AbstractView;

public interface NewsDetailContract {

    interface View extends AbstractView {

        void getNewsDetailSuccess(NewsDetailBean newsDetailBean);
    }

    interface Presenter extends AbstractPresenter<View> {
        void getNewsDetail(int id);
    }
}
package com.zyj.plugin.fragment;


import com.zyj.plugin.common.data.bean.HomeBean;
import com.zyj.plugin.common.mvp.presenter.AbstractPresenter;
import com.zyj.plugin.common.mvp.view.BaseView;

/**
 * @author quchao
 * @date 2017/11/28
 */

public interface HomeContract {

    interface View extends BaseView {
        void getHomeDataSuccess(HomeBean homeAdBean);
    }

    interface Presenter extends AbstractPresenter<View> {
        void getHomeData(int scenicId);
    }

}

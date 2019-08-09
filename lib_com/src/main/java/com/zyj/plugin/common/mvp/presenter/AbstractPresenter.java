package com.zyj.plugin.common.mvp.presenter;


import com.zyj.plugin.common.mvp.view.BaseView;

import io.reactivex.disposables.Disposable;

/**
 * Presenter 基类
 * 绑定和解绑view
 */
public interface AbstractPresenter<V extends BaseView> {

    /**
     * 注入View
     *
     * @param view view
     */
    void attachView(V view);

    /**
     * 回收View
     */
    void detachView();

    /**
     * Add rxBing subscribe manager
     *
     * @param disposable Disposable
     */
    void addRxBindingSubscribe(Disposable disposable);

}

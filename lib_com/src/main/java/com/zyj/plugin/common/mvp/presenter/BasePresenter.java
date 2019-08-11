package com.zyj.plugin.common.mvp.presenter;

import android.app.Activity;

import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.data.bean.BaseResponse;
import com.zyj.plugin.common.data.bean.BaseResponseV2;
import com.zyj.plugin.common.data.observer.BaseObserver;
import com.zyj.plugin.common.data.utils.RxUtils;
import com.zyj.plugin.common.mvp.view.AbstractView;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Description: <BasePresenter><br>
 * Author:      mxdl<br>
 * Date:        2018/1/15<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BasePresenter<V extends AbstractView> implements AbstractPresenter<V> {

    protected V mView;
    protected Activity activity;
    protected DataManager dataManager;
    private CompositeDisposable compositeDisposable;

    public BasePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(V view) {
        this.mView = view;
        this.activity = mView.getActivityContext();
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addSubscribe(disposable);
    }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    protected <E> void addSubscribe(Observable<BaseResponse<E>> observable, BaseObserver<E> observer) {
        Disposable disposable = observable.compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .subscribeWith(observer);
        addSubscribe(disposable);
    }

    protected <E> void addSubscribeV2(Observable<BaseResponseV2<E>> observable, BaseObserver<E> observer) {
        Disposable disposable = observable.compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResultV2())
                .subscribeWith(observer);
        addSubscribe(disposable);
    }
}

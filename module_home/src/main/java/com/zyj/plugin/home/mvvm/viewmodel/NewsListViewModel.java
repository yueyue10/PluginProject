package com.zyj.plugin.home.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.zyj.plugin.common.data.bean.NewsBean;
import com.zyj.plugin.common.event.SingleLiveEvent;
import com.zyj.plugin.common.mvvm.viewmodel.BaseViewModel;
import com.zyj.plugin.common.uitl.NetUtil;
import com.zyj.plugin.home.mvvm.model.NewsListModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: <NewsListPresenter><br>
 * Author:      mxdl<br>
 * Date:        2019/5/28<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class NewsListViewModel extends BaseViewModel<NewsListModel> {

    private SingleLiveEvent<List<NewsBean>> mNewsListSingleLiveEvent;

    public NewsListViewModel(@NonNull Application application, NewsListModel model) {
        super(application, model);
    }

    public void getNewsList(final int id) {
        if (!NetUtil.checkNetToast()) {
            postShowNetWorkErrViewEvent(true);
            return;
        }
        mModel.getNewsList(id).subscribe(new Observer<List<NewsBean>>() {
            @Override
            public void onSubscribe(Disposable d) {
                postShowInitLoadViewEvent(true);
            }

            @Override
            public void onNext(List<NewsBean> newsBeans) {
                if (newsBeans != null && newsBeans.size() > 0) {
                    getNewsListSingleLiveEvent().postValue(newsBeans);
                } else {
                    postShowNoDataViewEvent(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                postShowInitLoadViewEvent(false);
            }

            @Override
            public void onComplete() {
                postShowInitLoadViewEvent(false);
            }
        });
    }

    public SingleLiveEvent<List<NewsBean>> getNewsListSingleLiveEvent() {
        return mNewsListSingleLiveEvent = createLiveData(mNewsListSingleLiveEvent);
    }

}

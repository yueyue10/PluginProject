package com.zyj.plugin.common.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.zyj.plugin.common.event.SingleLiveEvent;
import com.zyj.plugin.common.mvvm.model.BaseModel;

import java.util.List;

/**
 * Description: <BaseViewRefreshModel><br>
 * Author:      mxdl<br>
 * Date:        2019/06/30<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class BaseViewRefreshModel<T, M extends BaseModel> extends BaseViewModel<M> {

    protected UIChangeRefreshLiveData mUIChangeRefreshLiveData;

    public BaseViewRefreshModel(@NonNull Application application, M model) {
        super(application, model);
    }

    public UIChangeRefreshLiveData getUCRefresh() {
        if (mUIChangeRefreshLiveData == null) {
            mUIChangeRefreshLiveData = new UIChangeRefreshLiveData();
        }
        return mUIChangeRefreshLiveData;
    }

    public final class UIChangeRefreshLiveData extends SingleLiveEvent {
        private SingleLiveEvent<Void> mStopRefresLiveEvent;
        private SingleLiveEvent<Void> mAutoRefresLiveEvent;
        private SingleLiveEvent<List<T>> mRefresLiveEvent;
        private SingleLiveEvent<List<T>> mLoadMoreLiveEvent;
        private SingleLiveEvent<Void> mStopLoadMoreLiveEvent;

        public SingleLiveEvent<Void> getStopRefresLiveEvent() {
            return mStopRefresLiveEvent = createLiveData(mStopRefresLiveEvent);
        }

        public SingleLiveEvent<Void> getAutoRefresLiveEvent() {
            return mAutoRefresLiveEvent = createLiveData(mAutoRefresLiveEvent);
        }

        public SingleLiveEvent<List<T>> getRefresLiveEvent() {
            return mRefresLiveEvent = createLiveData(mRefresLiveEvent);
        }

        public SingleLiveEvent<List<T>> getLoadMoreLiveEvent() {
            return mLoadMoreLiveEvent = createLiveData(mLoadMoreLiveEvent);
        }

        public SingleLiveEvent<Void> getStopLoadMoreLiveEvent() {
            return mStopLoadMoreLiveEvent = createLiveData(mStopLoadMoreLiveEvent);
        }
    }

    public void postStopRefreshEvent() {
        if (mUIChangeRefreshLiveData != null) {
            mUIChangeRefreshLiveData.getStopRefresLiveEvent().call();
        }
    }

    public void postAutoRefreshEvent() {
        if (mUIChangeRefreshLiveData != null) {
            mUIChangeRefreshLiveData.getAutoRefresLiveEvent().call();
        }
    }

    public void postRefreshDataEvent(List<T> list) {
        if (mUIChangeRefreshLiveData != null) {
            mUIChangeRefreshLiveData.getRefresLiveEvent().postValue(list);
        }
    }

    public void postLoadMoreEvent(List<T> list) {
        if (mUIChangeRefreshLiveData != null) {
            mUIChangeRefreshLiveData.getLoadMoreLiveEvent().postValue(list);
        }
    }

    public void postStopLoadMoreEvent() {
        if (mUIChangeRefreshLiveData != null) {
            mUIChangeRefreshLiveData.getStopLoadMoreLiveEvent().call();
        }
    }
}

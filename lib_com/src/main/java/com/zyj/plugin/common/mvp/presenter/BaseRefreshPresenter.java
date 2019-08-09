package com.zyj.plugin.common.mvp.presenter;

import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.mvp.contract.BaseRefreshContract;
import com.zyj.plugin.common.mvp.view.BaseRefreshView;

/**
 * Description: <BaseRefreshPresenter><br>
 * Author:      mxdl<br>
 * Date:        2018/2/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseRefreshPresenter<V extends BaseRefreshView<T>, T> extends BasePresenter<V> implements BaseRefreshContract.Presenter {
    protected DataManager dataManager;

    public BaseRefreshPresenter(DataManager dataManager) {
        super(dataManager);
    }
}

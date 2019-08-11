package com.zyj.plugin.common.mvp.presenter;

import com.zyj.plugin.common.data.DataManager;
import com.zyj.plugin.common.mvp.contract.BaseRefreshContract;
import com.zyj.plugin.common.mvp.view.AbstractView;

/**
 * Description: <BaseRefreshPresenter><br>
 * Author:      mxdl<br>
 * Date:        2018/2/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseRefreshPresenter<V extends AbstractView> extends BasePresenter<V> implements BaseRefreshContract.Presenter {
    /**
     * 页面标签
     */
    public int pageIndex = 0;

    public BaseRefreshPresenter(DataManager dataManager) {
        super(dataManager);
    }

    protected abstract void getRefreshData(int offset);

    @Override
    public void getLoadMoreData() {
        pageIndex++;
        int offset = (pageIndex - 1) * 10;
        getRefreshData(offset);
    }

    @Override
    public void getRefreshData() {
        pageIndex = 1;
        int offset = (pageIndex - 1) * 10;
        getRefreshData(offset);
    }
}

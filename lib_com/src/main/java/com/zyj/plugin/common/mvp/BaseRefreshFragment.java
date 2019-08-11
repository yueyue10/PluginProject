package com.zyj.plugin.common.mvp;

import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zyj.plugin.common.mvp.contract.BaseRefreshContract;
import com.zyj.plugin.common.mvp.presenter.BaseRefreshPresenter;

import java.util.ArrayList;

/**
 * Description: <下拉刷新、上拉加载更多的Fragment><br>
 * Author:      gxl<br>
 * Date:        2018/2/25<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseRefreshFragment<P extends BaseRefreshPresenter> extends BaseMvpFragment<P> implements BaseRefreshContract.View {

    protected SmartRefreshLayout mRefreshLayout;

    @Override
    public void initCommonView(View view) {
        super.initCommonView(view);
        initRefreshView(view);
    }

    public void initRefreshView(View view) {
        mRefreshLayout = view.findViewById(onBindRefreshLayout());
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            clearRefreshData();
            mPresenter.getRefreshData();
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> mPresenter.getLoadMoreData());
        mRefreshLayout.autoRefresh();
    }

    /**
     * 绑定RefreshLayout
     */
    protected abstract int onBindRefreshLayout();

    /**
     * 配置RefreshLayout
     */
    protected abstract void configRefreshLayout();

    /**
     * 清除RefreshLayout里面需要刷新的数据
     */
    protected abstract void clearRefreshData();

    @Override
    public void enableRefresh(boolean b) {
        mRefreshLayout.setEnableRefresh(b);
    }

    @Override
    public void enableLoadMore(boolean b) {
        mRefreshLayout.setEnableLoadMore(b);
    }

    @Override
    public void stopRefreshView() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void autoLoadData() {
        if (mRefreshLayout != null)
            mRefreshLayout.autoRefresh();
    }

    @Override
    public void clearData(ArrayList<?>... lists) {
        for (ArrayList<?> list1 : lists) {
            list1.clear();
        }
    }
}

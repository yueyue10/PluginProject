package com.zyj.plugin.common.mvp;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zyj.plugin.common.mvp.contract.BaseRefreshContract;
import com.zyj.plugin.common.mvp.presenter.BaseRefreshPresenter;

import java.util.ArrayList;

/**
 * Description: <下拉刷新、上拉加载更多的Activity><br>
 * Author:      gxl<br>
 * Date:        2018/2/26<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseRefreshActivity<P extends BaseRefreshPresenter> extends BaseMvpActivity<P> implements BaseRefreshContract.View {

    protected SmartRefreshLayout mRefreshLayout;

    @Override
    protected void initCommonView() {
        super.initCommonView();
        initRefreshView();
        configRefreshLayout();
    }

    public void initRefreshView() {
        mRefreshLayout = findViewById(onBindRefreshLayout());
        // 下拉刷新
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            clearRefreshData();
            mPresenter.getRefreshData();
        });
        // 上拉加载
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> mPresenter.getLoadMoreData());
        // 自动加载
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
    public void autoLoadData() {
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void stopRefreshView() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void clearData(ArrayList<?>... lists) {
        for (ArrayList<?> list1 : lists) {
            list1.clear();
        }
    }
}

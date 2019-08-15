package com.zyj.plugin.common.mvvm;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zyj.plugin.common.R;
import com.zyj.plugin.common.mvvm.view.IBaseRefreshView;
import com.zyj.plugin.common.mvvm.viewmodel.BaseViewRefreshModel;

import java.util.ArrayList;

/**
 * Description: <下拉刷新、上拉加载更多的Activity><br>
 * Author:      mxdl<br>
 * Date:        2019/07/02<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public abstract class BaseMvvmRefreshActivity<VM extends BaseViewRefreshModel> extends BaseMvvmActivity<VM> implements IBaseRefreshView {

    protected RecyclerView recyclerView;
    protected SmartRefreshLayout mRefreshLayout;

    @Override
    protected void initCommonView() {
        super.initCommonView();
        initRefreshView();
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initBaseViewObservable() {
        super.initBaseViewObservable();
        initBaseViewRefreshObservable();
    }

    private void initBaseViewRefreshObservable() {
        mViewModel.getUCRefresh().getAutoRefresLiveEvent().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                autoLoadData();
            }
        });
        mViewModel.getUCRefresh().getStopRefresLiveEvent().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                mRefreshLayout.finishRefresh();
            }
        });
        mViewModel.getUCRefresh().getStopLoadMoreLiveEvent().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                mRefreshLayout.finishLoadMore();
            }
        });
    }

    public void initRefreshView() {
        mRefreshLayout = findViewById(onBindRefreshLayout());
        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            clearRefreshData();
//            mPresenter.getRefreshData();
        });
//        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> mPresenter.getLoadMoreData());
        mRefreshLayout.autoRefresh();
    }

    protected abstract int onBindRefreshLayout();

    /**
     * 清除RefreshLayout里面需要刷新的数据
     */
    protected abstract void clearRefreshData();

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

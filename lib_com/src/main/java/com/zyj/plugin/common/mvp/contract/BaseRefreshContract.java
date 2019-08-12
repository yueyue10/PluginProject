package com.zyj.plugin.common.mvp.contract;

import com.zyj.plugin.common.mvp.view.AbstractView;

import java.util.ArrayList;

/**
 * Description: <基本的刷新数据协议><br>
 * Author:      mxdl<br>
 * Date:        2018/2/25<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public interface BaseRefreshContract {
    interface Presenter {
        void getRefreshData();

        void getLoadMoreData();
    }

    interface View extends AbstractView {

        void stopRefreshView();

        void autoLoadData();

        void clearData(ArrayList<?>... lists);

        void configRefreshLayout();

        void configRefreshLayout(boolean enableRefresh, boolean enableLoadMore, boolean autoLoadMore);
    }
}
